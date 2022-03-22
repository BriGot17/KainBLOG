
import React, { useState } from 'react'
import {CKEditor} from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { categoryOptions, connectionInfo, editorConfig } from './configs'
import './Editor.css'
import './Forms.css'
import './App.css'
import axios from 'axios'
import { Link } from 'react-router-dom'
import {default as ReactSelect, components} from "react-select"

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

class ArticleForm extends React.Component {
    
    
    constructor(props){
        super(props);
        
        this.state = props.article;
        console.log(this.state)
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.fileInput = React.createRef();
    }
    
    handleSelectChange = (selected) => {
        this.setState({
            category: selected
          });
    }
    handleChange(event){
        
        const target = event.target;
        const name = target.name;
        if(target.type === 'file'){
            event.preventDefault()
            alert(this.fileInput.current.files[0])
            this.state.image = event.target.files[0];
        } else{
            event.preventDefault()
            this.setState({
                [name]: target.value
            });
        }
        
    }
    handleSubmit(event){
        console.log(this.state); 
        let json = this.state
        let cats = this.state.category
        let values = cats.map(e => e.value)
        //console.log(JSON.stringify(json))
        json.category = values
        //console.log(JSON.stringify(json))
       
        const formData = new FormData();
            
            formData.append("title", this.state.title);
            formData.append("description", this.state.description);
            formData.append("text", this.state.text);
            formData.append("category", json.category);
            if(this.image != null){
                formData.append("picture", this.state.image)
            }
            const headers = {'Content-Type': 'multipart/form-data'};
              
       console.log(this.state.articleId != null);
        if(this.state.articleId != null){
            
            axios.patch(connectionInfo + "article/edit", formData, {headers})
                .then(response => alert(response.status));
        }
        else{
            
            axios.post(connectionInfo + "article/new", formData, {headers})
                .then(response => alert(response.status));
        }
    }
    

    render(){
        return (
            
            <div className='center'> 
                <form>
                    <input name="title" onChange={this.handleChange} type="text" value={this.state.title} id="title"  placeholder='Titel' className="formElement"/> <br/>
                    <textarea name="description" id="desc" onChange={this.handleChange} defaultValue={this.state.description === null ? "Kurzbeschreibung" : this.state.description} className='formElement'></textarea> <br/>

                    <label className="fileInput formElement">
                        <input type="file" name='picture' id="pic" aria-label="Titelbild" onChange={this.handleChange} ref={this.fileInput}/>
                        <span className="customFileInput"></span>
                    </label>

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
                            onChange={this.handleSelectChange}
                            allowSelectAll={true}
                            value={this.state.category}
                        />
                    </div>
                          
                    <div className="formElement editorContainer">
                        <CKEditor 
                            editor={ClassicEditor}
                            data={this.state.text}
                            onChange={(event, editor) => {
                                const data = editor.getData();
                                this.setState({
                                    ['text']: data
                                });
                                
                            }}
                        />
                    </div>
                    
                    <button type='button' onClick={this.handleSubmit} className='formElement button'>Einreichen 
                    </button>
                </form>    
            </div>
        )
    }
}

export default ArticleForm