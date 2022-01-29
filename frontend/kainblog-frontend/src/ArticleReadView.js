import './App.css'
import './artikel.css'
import {Route, Link, Routes, useParams} from 'react-router-dom';
import Artikel from  './artikel';
import React, {Component, Fragment, useEffect, useState} from 'react';
import DOMPurify from 'dompurify'

function ArticleView() {
    const params = useParams();
    const [article, setArticle] = useState({text: ''});
    
    
    const articleData = () => {
        fetch(`http://192.168.0.190:8080/article/${params.guid}`)
        .then((res) => res.json())
        .then((data) => setArticle(data))
    };
    useEffect(() => {
        articleData()
      }, []);


    return (
        <div>
            <div dangerouslySetInnerHTML={{__html: DOMPurify.sanitize(article.text)}} className='articleView center'></div>
            <Link to={`/article/edit/${params.guid}`}>
                <button >Edit</button>
            </Link>
            
        </div>
        
        
    )
}
export default ArticleView;