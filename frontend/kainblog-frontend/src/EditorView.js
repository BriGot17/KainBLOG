import './App.css';
//import Artikel from  './artikel';
import React, {useState} from 'react';
//import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import ArticleEditor from './EditorForm';
import './Editor.css'
function editorView(props) {
    var item;
    var titleText = '';
    var descText = '';
    try{
        item = props.item
    }catch (e){
        console.log("This is an error")
        //console.error(e);
    }
    if(item != null){
        titleText = item.title;
        descText = item.desc;
    }

    return(
        <div className="contentWrapper">
            <div className="editorContainer">
                <ArticleEditor/>
            </div>
            
        </div>
        
    )

}

export default editorView();