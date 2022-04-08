import './App.css'
import './artikel.css'
import {Link} from 'react-router-dom';
import AuthService from "./services/AuthService";


function ArticleNav(props) {
        
        const roles = () => {
            let r = ["NULL"];
            try{
                console.log("here");
                r = JSON.parse(localStorage.getItem("user")).roles;
            }catch{
                console.log("catched");
                r = [];
            }
            console.log(r);
            return r;
        };
        let showLink = roles().includes('ADMIN') ? true : roles().includes('PUBLISHER') ? true: false;
        const token = () =>{
            let t;
            try{
                t = localStorage.getItem("user").token;
            }catch{
                t = "";
            }
            return t;
        }
        let showProfile = token() !== "" ? true : false;
       
        console.log(roles()) 

        return (
            <div className='sideNavContainer'>
                <div className='buttonContainer'>
                        <Link className='navLink' to={'/'}>
                            <button className='button navButton'>
                                Hauptseite
                            </button>
                        </Link>

                        {
                            showLink ? <Link className="navlink" to={'article/new'}>
                            <button className='button navButton'>
                                Add new Article 
                            </button>
                        </Link> : <></>
                        }
                        <Link className='navLink' to={'login'}>
                            <button className='button navButton'>
                                Login
                            </button>
                        </Link>
                        {
                            showProfile ? <Link className="navLink" to={'users/gotped17'}>
                            <button className='button navButton'>
                                Profil
                            </button>
                        </Link> :<></>
                        }
                        
                </div>
            </div>
        )
    
}
export default ArticleNav;