import './App.css';
import {BrowserRouter as Router, Route, Link, Routes} from 'react-router-dom';
import EditorView from './EditorView';
import ArticleList  from './articleList';

//var XMLParser = require('react-xml-parser');

function App() {
  
  return (
    <div className="App" >
      <header className="App-header">

      </header>
      <Router>
        <Routes>
          <Route path="/" element={ArticleList()} />
          <Route path="/article/new" element={EditorView}/>
          
        </Routes>
      </Router>
    </div>
  );
}//

export default App;
