import axios from "axios";
import { connectionInfo } from "../configs";
import AuthService from "./AuthService"

const API_URL = connectionInfo;
class ArticleService{
    
    async saveNewArticle(json){
        const user = JSON.parse(localStorage.getItem('user'));
        
        if(AuthService.checkAuthentication){
                await axios.post(API_URL + "article/new", JSON.stringify(json), {
                headers: {
                    Authorization : `Bearer ${user.token}`,
                    'Content-Type' : "application/json"
                }
                }).then(data =>  {
                    //console.log(data);
                    
                    if(data.status === 200){
                        return true;
                    }else {
                        return false;
                    }
                });
        }
        else{
            throw new Error("Not authenticated");
        }
        
    }

    async editArticle(json){
        const user = JSON.parse(localStorage.getItem('user'));
        
        if(AuthService.checkAuthentication){
                await axios.patch(API_URL + "article/edit", JSON.stringify(json), {
                    headers: {
                    Authorization : `Bearer ${user.token}`,
                    'Content-Type' : "application/json"
                }
                }).then(data => {
                    //console.log(data);
                    
                    if(data.status === 200){
                        return true;
                    }else {
                        return false;
                    }
                });
        }
        else{
            throw new Error("Not authenticated");
        }
        
    }
}
export default new ArticleService();