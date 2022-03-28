import axios from "axios";
import { connectionInfo } from "../configs";
import { buildEssentialHeaders, buildAuthHeader } from "./headerBuilder"
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
  async getCurrentUser() {
    const user = JSON.parse(localStorage.getItem('user'));
    return await axios.get(
      API_URL + "users/current",  {
        headers: {
          Authorization: `Bearer ${user.token}`
        }
      }
    ).then(res => {
      console.log(res.data)
    })
  }

  checkAuthentication(){
    return buildAuthHeader != {} ? true : false;
  }
}
export default new AuthService();