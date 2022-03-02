import React, { useState } from 'react'
import { categoryOptions, editorConfig } from './configs'
import './Editor.css'
import './Forms.css'
import './App.css'
import axios from 'axios'
import { Link } from 'react-router-dom'

export default function Login(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function validateForm() { //Primitive validation check
        return username.length > 0 && password.length > 0;
    }

    function handleSubmit(){
        
    }

    return (
        <div className="loginContainer">
            <form>
                <input className="formElement" type="text" placeholder="Username" name="username" onChange={(e) => setUsername(e.target.value)}/>

                <input className="formElement" type="password" placeholder="Password" name="password" onChange={(e) => setPassword(e.target.value)}/>
                
                <button type='button' disabled={!validateForm()} onClick={handleSubmit()} className='formElement button'>Login</button>
            </form>
        </div>
    )
}