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
    
    
    
    const fetchArticle = async () =>{
            let res = await fetch(`http://192.168.0.190:8080/article/${params.guid}`);
            let json = await res.json();
            let rawCategories = json.category;
            let categories = [];
            
            rawCategories.forEach((x) => {
                x.replace("_", " und ")
                categories.push({value: x, label: x.charAt(0).toUpperCase() + x.slice(1)});
            })
            json.category = categories;
            setArticle(json);
            console.log(json)
            //console.log("guid passed");
        
    };

    useEffect(() => {
        let guid = params.guid;
        let guidExist = false;
        
        if(guid != null){
            fetchArticle();
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