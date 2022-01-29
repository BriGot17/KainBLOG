import './artikel.css';

function Artikel(props){
    //var categories = props.categories;
    var categories = String(props.tags).split('/');
    var color = '#72326c';
    if(categories.includes('informatik'))
        color = '#5d8ec6';
    else if(categories.includes('automatisierung'))
        color = '#b5213a';
    else if(categories.includes('mechatronik'))
        color = '#cb9e10';
    else if(categories.includes('robotik'))
        color = '#008000';
    
    return (
    <div className="artikelContainer" >
        <div  className="artikelHeadline" style={{backgroundColor: color}}>
            <h2>{props.title}</h2>
        </div>
        <div className="descContainer">
            <p className="artikelDescription">{props.desc}</p>
            <img src={props.img} className="artikelImage"></img>
            
        </div>
    </div>
    )
    
}
    
export default Artikel;