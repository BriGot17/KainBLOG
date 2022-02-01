import './App.css'
import './artikel.css'
import {Route, Link, Routes} from 'react-router-dom';
import Artikel from  './artikel';
import React, {Component, useEffect, useState} from 'react';


function ArticleList(){
  const RSS_URL = `http://localhost:8080/rss/feed/`;
  const [items, setItems] = useState([]);
  useEffect(() => {
    

    const getRss = async (e) => {
    const res = await fetch(RSS_URL);
    const contents = await res.text();
    const feed = new window.DOMParser().parseFromString(contents, "text/xml");
    const items = feed.querySelectorAll("item");

    const feedItems = [...items].map((el) => ({
      link: el.querySelector("link").innerHTML,
      title: el.querySelector("title").innerHTML,
      description: el.querySelector("description").innerHTML,
      guid: el.querySelector("guid").innerHTML,
      enclosure: el.querySelector("enclosure").getAttribute("url"),
      categories: el.querySelector("category").innerHTML
    }));
    setItems(feedItems);
  };
    getRss()
  }, []);
  
  return (
    <div className="contentWrapper">
      <div className="filterContainer">
      
      </div>
      <script></script>

      <div className="artikelList">
        {
          items.map(item => (
            <Link to={`/article/${item.guid}`} className="articleLink" >
              <Artikel img={item.enclosure} title={item.title} desc={item.description} link={item.link} tags={item.categories} />
            </Link>))
        } 

        <span style={{display: 'none'}}>
        <Artikel desc="" />
        </span>
        
      </div>


      
    </div>
    
  )
}
export default ArticleList;