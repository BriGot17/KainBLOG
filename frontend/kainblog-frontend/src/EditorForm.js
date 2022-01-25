
import React, { useState } from 'react'
import {CKEditor} from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { categoryOptions, editorConfig } from './configs'
import './Editor.css'
import './Forms.css'
import './App.css'
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
        this.state = {};
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
            this.setState({
                [name]: this.fileInput.current.files[0]
            });
        } else{
            event.preventDefault()
            this.setState({
                [name]: target.value
            });
        }
        
    }
    handleSubmit(event){
        
        let json = this.state
        let cats = this.state.category
        let values = cats.map(e => e.value)
        console.log(JSON.stringify(json))
        json.category = values
        console.log(JSON.stringify(json))
       
        
        const reqOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(json)
        };
        fetch('http://192.168.0.190:8080/article/new', reqOptions)
            .then(response => alert(response.status));
        this.setState({})
        this.clearFields()
    }
    
    clearFields(){
        document.getElementById("title").value = '';
        document.getElementById("desc").value = 'Kurzbeschreibung';
        document.getElementById("catSelect").value = null;
        
    }
    render(){
        return (
            
            <div className='center'> 
                <form>
                    
                    <input onChange={this.handleChange} type="text" id="title" name="title" placeholder='Titel' className="formElement"/> <br/>
                    <textarea name="description" id="desc" onChange={this.handleChange} className='formElement' defaultValue="Kurzbeschreibung"></textarea> <br/>
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
                            onChange={(event, editor) => {
                                const data = editor.getData();
                                this.setState({
                                    ['text']: data
                                });
                            }}
                        />
                    </div>
                    
                    <button type='button' onClick={this.handleSubmit} className='formElement button'>Submit</button>
                </form>    
            </div>
        )
    }
}

export default ArticleForm