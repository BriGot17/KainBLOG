import './App.css';
//import Artikel from  './artikel';
import React, {useEffect, useState} from 'react';
//import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import ArticleEditor from './EditorForm';
import './Editor.css'
import {Link, useParams} from 'react-router-dom';
function EditorView() {
    const params = useParams();
    const [article, setArticle] = useState(null);
    
    
    
    const getArticle = async () =>{
            let res = await fetch(`http://192.168.0.190:8080/article/${params.guid}`);
            let json = await res.json();
            setArticle(json);
            console.log("guid passed");
        
    };

    useEffect(() => {
        let guid = params.guid;
        let guidExist = false;
        
        if(guid != null){
            getArticle();
        }
        else{
            setArticle({text: ''})
        }
       //console.log(article) 
    }, [])

    return article === null ? <span>Loading ...</span> : (
        <div className="contentWrapper">
            <div className="editorContainer">
                <ArticleEditor article={article}/>
            </div>
            
        </div>
        
    )

}

export default EditorView;