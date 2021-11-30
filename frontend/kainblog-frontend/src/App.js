import logo from './logo.svg';
import './App.css';
import Artikel from  './artikel';
import React, {useState} from 'react';
//var XMLParser = require('react-xml-parser');

function App() {
  const RSS_URL = `https://www.kleinezeitung.at/rss/sport`;
  const [items, setItems] = useState([]);

  const getRss = async (e) => {
    const res = await fetch(RSS_URL);
    const  contents  = await res.text();
    
    //var parser = new XMLParser();
    //const feed = parser.parseFromString(contents);
    //const xml = feed.querySelectorAll("item");
    const feed = new window.DOMParser().parseFromString(contents, "text/xml");
    const items = feed.querySelectorAll("item");
    const feedItems = [...items].map((el) => ({
      link: el.querySelector("link").innerHTML,
      title: el.querySelector("title").innerHTML,
      description: el.querySelector("description").innerHTML,
      guid: el.querySelector("guid").innerHTML,
      enclosure: el.querySelector("enclosure").getAttribute("url")
    }));
    setItems(feedItems);
  };
  return (
    <div className="App" onLoad={getRss}>
      <header className="App-header">

      </header>
      <div className="filterContainer">xcv</div>

      <div className="artikelList">
        {
          items.map(item => <Artikel img={item.enclosure} title={item.title} desc={item.description} link={item.link}  />)
        }
        <span style={{display: 'none'}}>
        <Artikel img={logo}/>
        </span>
        
      </div>
      
    </div>
  );
}

export default App;
