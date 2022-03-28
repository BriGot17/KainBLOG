import './App.css'
import './artikel.css'
import {Link, useParams} from 'react-router-dom';
import React, {useEffect, useState} from 'react';
import DOMPurify from 'dompurify'
import PublicService from './services/PublicService';

function ArticleView() {
    const params = useParams();
    const [article, setArticle] = useState({text: ''});
    
    
    const articleData = async () => {
        await PublicService.getOneArticle(params.guid).then(res => {
            setArticle(res.data)
        })
    
    };
    useEffect(() => {
        articleData()
      }, []);


    return (
        <div>
            <h1>{article.title}</h1>
            <p>{article.description}</p>
            <div dangerouslySetInnerHTML={{__html: DOMPurify.sanitize(article.text)}} className='articleView center'></div>
            <Link to={`/article/edit/${params.guid}`}>
                <button >Edit</button>
            </Link>
            
        </div>
        
        
    )
}
export default ArticleView;