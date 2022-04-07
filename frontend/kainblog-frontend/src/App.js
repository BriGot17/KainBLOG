import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import EditorView from './EditorView';
import ArticleList  from './articleList';
import ArticleNav from './ArticleNav';
import ArticleView from './ArticleReadView';
import Login from './Login';
import Profile from './Profile';
import AuthService from './services/AuthService';


//var XMLParser = require('react-xml-parser');

function App() {

  let authenticated = AuthService.checkAuthentication();
  if(!authenticated){
    console.log("asdf");
    //AuthService.logout();
  }
return (
    <div className="App" >
      <header className="App-header">

      </header>
      
      <Router>
        <ArticleNav />
        <div className="contentContainer">
        <Routes>
          <Route path="/" element={ArticleList()} />
          <Route path="/login" element={<Login />}/>
          <Route path="/users/:username" element={<Profile />}/>
          <Route path="/article/new" element={<EditorView />}/>
          <Route path="/article/edit/:guid" element={<EditorView />} />
          <Route path={"/article/:guid"} element={<ArticleView />}/>   
          <Route path="/login" element={Login()}/>
        </Routes>
      
        </div>  
      </Router>  
        
    </div>
);
}

export default App;