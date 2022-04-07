import './App.css'
import './artikel.css'
import {Link} from 'react-router-dom';



function ArticleNav(props) {
/*
        const roles = () => {
            let r = ["NULL"];
            try{
                console.log("here");
                r = localStorage.getItem("user").username;
            }catch{
                console.log("catched");
                r = null;
            }
            console.log(r);
            return r;
        };
        const token = () =>{
            let t;
            try{
                t = localStorage.getItem("user").token;
            }catch{
                t = "";
            }
            return t;

        }
       
        console.log(roles()) 
*/
        return (
            <div className='sideNavContainer'>
                <div className='buttonContainer'>
                        <Link className='navLink' to={'/'}>
                            <button className='button navButton'>
                                Hauptseite
                            </button>
                        </Link>

                        <Link className="navlink" to={'article/new'}>
                            <button className='button navButton'>
                                Add new Article 
                            </button>
                        </Link>
                        <Link className='navLink' to={'login'}>
                            <button className='button navButton'>
                                Login
                            </button>
                        </Link>

                        <Link className="navLink" to={'users/gotped17'}>
                            <button className='button navButton'>
                                Profil
                            </button>
                        </Link>
                </div>
            </div>
        )
    
}
export default ArticleNav;