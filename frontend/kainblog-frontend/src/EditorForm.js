
import React, { useState } from 'react'
import {CKEditor} from '@ckeditor/ckeditor5-react'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'
import { editorConfig } from './configs'


class ArticleForm extends React.Component {
    constructor(props){
        super(props);
        this.state = {};
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    
    handleChange(event){
        event.preventDefault()
    }
    handleSubmit(event){

    }

    render(){
        return (
            <div> 
                <form onSubmit={this.handleSubmit}>        
                <input type="text" name="title" />           
        
                    <CKEditor
                        editor={ClassicEditor}
                        onChange={(event, editor) => {
                            const data = editor.getData();
                            this.state.text = data;
                        }}
                    />
                    <button type='submit'>Submit</button>
                </form>    
            </div>
        )
    }
}

export default ArticleForm