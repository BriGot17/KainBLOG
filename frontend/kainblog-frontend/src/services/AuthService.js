import axios from "axios";
import { connectionInfo } from "../configs";
import authHeader from "./authHeader";
const API_URL = connectionInfo;
class AuthService {

  login(username, password) {
    return axios
      .post(API_URL + "signin", {
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
  getCurrentUser() {
    const headers = authHeader();
  
    return axios.get(
      API_URL + "users/current", headers, headers
    ).then(res => {
      console.log(res.data)
    })
    return JSON.parse(localStorage.getItem('user'));;
  }

  checkAuthentication(){
    return authHeader() != {} ? true : false;
  }
}
export default new AuthService();