import './App.css';
import {BrowserRouter as Router, Route, Link, Routes} from 'react-router-dom';
import EditorView from './EditorView';
import ArticleList  from './articleList';
import ArticleEditor from './EditorForm';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import ArticleNav from './ArticleNav';
import ArticleView from './ArticleReadView';
import Login from './Login';
import Profile from './Profile';

//var XMLParser = require('react-xml-parser');

function App() {
  
return (
    <div className="App" >
      <header className="App-header">

      </header>
      
      <Router>
        <ArticleNav />
        
        
        <Routes>
          
          <Route path="/" element={ArticleList()} />
          <Route path="/login" element={<Login />}/>
          <Route path="/profile" element={<Profile />}/>
          <Route path="/article/new" element={<EditorView />}/>
          <Route path="/article/edit/:guid" element={<EditorView />} />
          <Route path={"/article/:guid"} element={<ArticleView />}/>    
        </Routes>
      </Router>
    </div>
);
}

export default App;