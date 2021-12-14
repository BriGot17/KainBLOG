import './artikel.css';

function Artikel(props){
    //var categories = props.categories;
    var categories = String(props.tags).split('/');
    var color = '#72326c';
    if(categories.includes('Informatik'))
        color = '#5d8ec6';
    else if(categories.includes('Automatisierung'))
        color = '#b5213a';
    else if(categories.includes('Mechatronik'))
        color = '#cb9e10';
    else if(categories.includes('Robotik'))
        color = '#008000';
    
    return (
        <a className='articleLink' href={props.link}>
            <div className="artikelContainer" >
        <div  className="artikelHeadline" style={{backgroundColor: color}}>
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