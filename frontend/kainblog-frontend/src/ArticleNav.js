import './App.css'
import './artikel.css'
import {Route, Link, Routes} from 'react-router-dom';
import Artikel from  './artikel';
import React, {Component, useEffect, useState} from 'react';

class ArticleNav extends React.Component{

    constructor(props){
        super(props)
    }

    render(){
        return (
            <div className='sideNavContainer'>
                <div className='buttonContainer'>
                        <Link className='navLink' to={'/'}>
                            <button className='button navButton'>
                                Hauptseite
                            </button>
                        </Link>

                        <Link className='navLink' to={'article/new'}>
                            <button className='button navButton'>
                                Add new Article 
                            </button>
                        </Link>

                        <Link className='navLink' to={'login'}>
                            <button className='button navButton'>
                                Login
                            </button>
                        </Link>

                        <Link className='navLink' to={'users/gotped17'}>
                            <button className='button navButton'>
                                Profil
                            </button>
                        </Link>
                </div>
            </div>
        )
    }
}
export default ArticleNav;