
import React, { useState } from 'react'
import {CKEditor} from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { categoryOptions} from './configs'
import './Editor.css'
import './Forms.css'
import './App.css'
import {default as ReactSelect, components} from "react-select"
import ArticleService from "./services/ArticleService"
import Modal from './modal';
import AuthService from './services/AuthService'

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
        const [status, setStatus] = useState("")

    
    const handleSelectChange = (selected) => {
        //console.log(AuthService.checkAuthentication());
        setCategory(selected);
    }

    const handleSubmit = (event) => {
        console.log(category);
        let cats = category
        cats = cats.map(e => e.value)
        
        const jsondata = 
        {   
            "title": title,
            "category": cats,
            "description": desc,
            "text": text,
        }

        
        console.log(JSON.stringify(jsondata))
        
        
              
        if(props.article.articleId != null){
            
            let hasSucceeded = ArticleService.editArticle(jsondata)
            if(hasSucceeded){
                setStatus("edit success")
                setIsOpen(true);
                
            }   
        }

        else{
            let hasSucceeded = ArticleService.saveNewArticle(jsondata);
            if(hasSucceeded){
                setStatus("new success")
                setIsOpen(true);
                
            }
               
            
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
                        value={category}
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
            {isOpen && <Modal setIsOpen={setIsOpen} event={status} />}    
        </div>
    )
    
}

export default ArticleForm