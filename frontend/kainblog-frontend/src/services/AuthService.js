import axios from "axios";
import { connectionInfo } from "../configs";
import { buildEssentialHeaders, buildAuthHeader } from "./headerBuilder"
import  jsonwebtoken from "jsonwebtoken";

const API_URL = connectionInfo;
class AuthService {

  login(username, password) {
    return axios
      .post(API_URL + "token/generate-token", {
        username,
        password
      })
      .then(response => {
        const data = response.data
        if (response.data.token) {

          localStorage.setItem("user", JSON.stringify(data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }
  register(username, password) {
    return axios.post(API_URL + "signup", {
      username,
      password
    });
  }
  async getCurrentUser() {
    const user = JSON.parse(localStorage.getItem('user'));
    return await axios.get(
      API_URL + "users/current",  {
        headers: {
          Authorization: `Bearer ${user.token}`
        }
      }
    )
  }

  //True => Jwt still valid
  //False => Jwt expired
  checkAuthentication(){
    let isExpired = false;
    const token = localStorage.getItem('user').token;
    let decodedToken=jsonwebtoken.decode(token, {complete: true});
    let dateNow = new Date();
    
    if(decodedToken === null || decodedToken.exp < dateNow.getTime())
      isExpired = true;

    return !isExpired;
  }
}
export default new AuthService();