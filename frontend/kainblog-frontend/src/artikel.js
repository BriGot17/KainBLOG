import './artikel.css';

function Artikel(props){
    return (
    <div className="artikelContainer">
        <div  className="artikelHeadline">
            <h2>{props.title}</h2>
        </div>
        <div className="descContainer">
            <img src={props.img} className="artikelImage"></img>
            <p className="artikelDescription">{props.desc}</p>
        </div>
    </div>)
    
}
    
export default Artikel;