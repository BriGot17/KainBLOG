import './artikel.css';

function Artikel(props){
    return (
        <a href={props.link}>
            <div className="artikelContainer">
        <div  className="artikelHeadline">
            <h2><a href={props.link}> {props.title} </a></h2>
        </div>
        <div className="descContainer">
            <p className="artikelDescription">{props.desc}</p>
            <img src={props.img} className="artikelImage"></img>
            
        </div>
    </div></a>
    )
    
}
    
export default Artikel;