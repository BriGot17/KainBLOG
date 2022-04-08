import './App.css';
//import Artikel from  './artikel';
import React, { useEffect, useState } from 'react';
//import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import ArticleEditor from './EditorForm';
import './Editor.css'
import { useParams } from 'react-router-dom';
import PublicService from './services/PublicService';

function EditorView() {
    const params = useParams();
    const [article, setArticle] = useState(null);
    const [data, setData] = useState({'': ''});


    const fetchArticle = async () => {
        
        setData((await PublicService.getOneArticle(params.guid)).data)
        console.log(data);
        let rawCategories = data.category;
        let categories = [];
        console.log(data)
        if (rawCategories[0] != null) {
            rawCategories.forEach((x) => {
                categories.push({ value: x, label: x.charAt(0).toUpperCase() + x.slice(1) });
            })
        }
        let d = data
        
        d.category = categories;
        setData(d)
        setArticle(data);
        console.log(data)
        //console.log("guid passed");

    };

    useEffect(() => {
        let guid = params.guid;

        if (guid != null) {
            fetchArticle();
        }
        else {
            setArticle({ text: '' })
        }
        //console.log(article) 
    }, [])

    return article === null ? <span>Loading ...</span> : (
        <div className="contentWrapper">
            <div className="editorContainer">
                <ArticleEditor article={article} />
            </div>

        </div>

    )

}

export default EditorView;