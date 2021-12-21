import './App.css'
import './artikel.css'
import {BrowserRouter as Router, Route, Link, Routes} from 'react-router-dom';
import Artikel from  './artikel';
import React, {useState} from 'react';

function ArticleList(){
    const RSS_URL = `http://192.168.0.190:8080/rss/feed/`;
  const [items, setItems] = useState([]);

  const getRss = async (e) => {
    const res = await fetch(RSS_URL);
    const  contents  = await res.text();
    console.log(contents);
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
  return (
    <Router>
    <div className="filterContainer">
      <Link to={'article/new'}>Add new Article</Link>
    </div>
    
      <div className="artikelList" onLoad={getRss}>
        {
          items.map(item => (
            <Link to={`/article/${item.guid}`}>
              <Artikel img={item.enclosure} title={item.title} desc={item.description} link={item.link} tags={item.categories} />
            </Link>))
        } 
        <span style={{display: 'none'}}>
        <Artikel desc="" />
        </span>
        
      </div>
      <Routes>
        {
          items.map(item => (
            <Route path={'/article/:guid'} />
          ))
        }
      </Routes>
      
    </Router>
  )
}
export default ArticleList;