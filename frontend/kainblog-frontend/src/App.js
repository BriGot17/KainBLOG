import logo from './logo.svg';
import './App.css';
import Artikel from  './artikel';
function App() {
  return (
    <div className="App">
      <header className="App-header">

      </header>
      <div className="filterContainer">xcv</div>

      <div className="artikelList">
        <Artikel img="hahlol" title="test123" desc="Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."/>
        <Artikel title="test456" desc="yxcybcv"/>
      </div>
      
    </div>
  );
}

export default App;
