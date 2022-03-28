
import React, { useState } from "react";
import "./Login.css";
import AuthService from "./services/AuthService";

function Login() {

  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);

  const errors = {
    user: "invalid username",
    pass: "invalid password"
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    let loginform = document.forms[0];

    let loginuser = loginform.user.value;
    let pass = loginform.pass.value;


    let loginResponse = AuthService.login(loginuser, pass)

    if (loginResponse != null) {
      console.log(AuthService.getCurrentUser())
      setIsSubmitted(true)
    } 
  };

  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div class="error">{errorMessages.message}</div>
    );

  const renderForm = (
    
    <div>
        <form onSubmit={handleSubmit}>
        <div>
            {renderErrorMessage("user")}
            <input type="text" placeholder="username" name="user" required/>
        </div>
        <br/>
        <div>
            {renderErrorMessage("pass")}
            <input type="password" placeholder="password" name="pass" required/>
        </div>
        <br/>
        <div>
            <button type="submit" className="button">Login</button>
        </div>
        </form>
    </div>
    
  );

  return (
    <div>
      <div>
        <br/><br/>
        <div>Sign In</div>
        {//isSubmitted ? <div>User is successfully logged in</div> : AuthService.checkAuthentication() ? <div>User is successfully logged in</div> : renderForm
          renderForm
        }
      </div>
    </div>
  );
}

export default Login;
