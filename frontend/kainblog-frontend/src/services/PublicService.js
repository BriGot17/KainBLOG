import axios from 'axios'
import { connectionInfo } from '../configs.js'
class PublicService {

    async getArticles(){
        return await axios.get(connectionInfo + "rss/feed", {
            responseType: 'text'
          }, {
              Accept: "application/xml"
          });
    }
    async getOneArticle(guid){
        return await axios.get(connectionInfo + `article/${guid}`, {
            responseType: 'text'
          }, {
              Accept: "application/json"
          })
    }
}
export default new PublicService();