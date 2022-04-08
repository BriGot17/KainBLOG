import axios from "axios";
import { connectionInfo } from "../configs";
import jsonwebtoken from "jsonwebtoken";

const API_URL = connectionInfo;
class AuthService {

  login(username, password) {
    return axios
      .post(API_URL + "token/generate-token", {
        username,
        password
      })
  }

  logout() {
    console.log("logging out");
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
      API_URL + "users/current", {
      headers: {
        Authorization: `Bearer ${user.token}`
      }
    })
  }

  //True => Jwt still valid
  //False => Jwt expired
  checkAuthentication() {
    try {
      let authenticated = false;
      const token = JSON.parse(localStorage.getItem('user')).token;
      let decodedToken = jsonwebtoken.decode(token, { complete: true });
      let dateNow = new Date();

      if (decodedToken === null || decodedToken.exp < dateNow.getTime()) {
        if (token === undefined) {
          console.log("huhu");
          authenticated = false;
        }
      }
      else {
        console.log("requesting");
        /*axios.post(API_URL + "token/check", {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }).then(res => {
          if (res.data === "ok") {
            console.log("its fine")
            authenticated = true;
          }
          else if (res.data === "netok") {
            console.log("failed");
            authenticated = false;
          } else {
            console.log("the heck you doing here");
          }
        })*/
      }
      return authenticated;
    } catch {
      console.error("Jwt missing");
      return false
    }
  }

}
export default new AuthService();