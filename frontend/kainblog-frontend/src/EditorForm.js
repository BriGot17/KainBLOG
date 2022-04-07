
import React, { useState } from 'react'
import {CKEditor} from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { categoryOptions, connectionInfo, editorConfig } from './configs'
import './Editor.css'
import './Forms.css'
import './App.css'
import axios from 'axios'
import {default as ReactSelect, components} from "react-select"
import ArticleService from "./services/ArticleService"
import Modal from './modal';

const Option = (props) => {
    return (
        <div>
        <components.Option {...props}>
          <input
            type="checkbox"
            checked={props.isSelected}
            onChange={() => null}
          />{" "}
          <label>{props.label}</label>
        </components.Option>
      </div>
    )
}

const ArticleForm = (props) => {
    
        const [isOpen, setIsOpen] = useState(false);
        const [title, setTitle] = useState(props.article.title != null ? props.article.title: "")
        const [desc, setDesc] = useState(props.article.desc != null ? props.article.desc: "")
        const [category, setCategory] = useState(props.article.category != null ? props.article.category: [])
        const [text, setText]  = useState(props.article.text != null ? props.article.text : "Hello")

        const [state, setState] = useState(props.artile != null ? props.article: {text: ""});
        
        
    
    
    const handleSelectChange = (selected) => {
        setState({
            category: selected
          });
    }
    /* 
    const handleChange = (event) => {
        
        const target = event.target;
        const name = target.name;


        if(target.type === 'file'){
            event.preventDefault()
            alert(fileInput.current.files[0])
            state.image = event.target.files[0];
        } else{
            event.preventDefault()
            setState({
                [name]: target.value
            });
        }
        
    }*/
    const handleSubmit = (event) => {
        
        let cats = category
        setState(cats.map(e => e.value))
        
       
        const formData = new FormData();
            
        const jsondata = 
        {   
            "title": title,
            "category": category,
            "description": desc,
            "text": text,
        }

        
        console.log(jsondata)
        
        
              
        if(state.articleId != null){
            
            ArticleService.saveNewArticle(jsondata)
                .then(res => {
                if(res.status === 200){
                    Modal(setIsOpen, "edit success")
                }if(res.status === 415){
                    Modal(setIsOpen, "")
                }
            });
        }

        else{
            let hasSucceeded = 
            ArticleService.saveNewArticle(jsondata)
                .then(res => {
                    if(res.status === 200){
                        setIsOpen(true);
                    }});
            
            //axios.post(connectionInfo + "article/new", formData, {headers})
            //    .then(response => alert(response.status));
        }
    }
    
    return (
        
        <div className='center'> 
            <form>
                <input name="title" onChange={e => setTitle(e.target.value)} type="text" value={title} id="title"  placeholder='Titel' className="formElement"/> <br/>
                <textarea name="description" id="desc" onChange={e => setDesc(e.target.value)} defaultValue={desc === null ? "Kurzbeschreibung" : desc} className='formElement'></textarea> <br/>

                <div
                    className="categorySelect center formElement"
                    data-toggle="popover"
                    data-trigger="focus"
                    data-content="Please select account(s)">
                    <ReactSelect
                        id="catSelect"
                        placeholder='Wähle Kategorien...'
                        name='category'
                        options={categoryOptions}
                        isMulti
                        closeMenuOnSelect={false}
                        hideSelectedOptions={false}
                        components={{
                            Option
                        }}
                        onChange={handleSelectChange}
                        allowSelectAll={true}
                        value={state.category}
                    />
                </div>
                        
                <div className="formElement editorContainer">
                    <CKEditor 
                        editor={ClassicEditor}
                        data={text}
                        onChange={(event, editor) => {
                            const data = editor.getData();  
                            setText(data);
                            
                        }}
                    />
                </div>
                
                <button type='button' onClick={handleSubmit} className='formElement button'>Einreichen 
                </button>
            </form>    
        </div>
    )
    
}

export default ArticleForm