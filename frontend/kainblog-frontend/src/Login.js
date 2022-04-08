
import React, { useState } from "react";
import "./Login.css";
import AuthService from "./services/AuthService";
import Modal from './modal';

function Login() {


  const [isSubmitted, setIsSubmitted] = useState(false);
  const [isOpen, setIsOpen] = useState(false);
  const [status, setStatus] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    let loginform = document.forms[0];

    let loginuser = loginform.user.value;
    let pass = loginform.pass.value;

    let loginResponse = AuthService.login(loginuser, pass)

    loginResponse
      .then(response => {
        const data = response.data

        if (response.data.token) {

          localStorage.setItem("user", JSON.stringify(data));
        }

        if (response.status === 200) {
          setIsOpen(true)
          setIsSubmitted(true)
          setStatus("login success")
        }
      });
  };

  const renderForm = (

    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <input type="text" placeholder="username" name="user" required />
        </div>
        <br />
        <div>
          <input type="password" placeholder="password" name="pass" required />
        </div>
        <br />
        <div>
          <button type="submit" className="button">Login</button>
        </div>
      </form>
    </div>

  );

  return (
    <div>
      <div>
        <br /><br />
        <div>Sign In</div>
        
        {isSubmitted ? <div>User is successfully logged in</div> : AuthService.checkAuthentication() ? <div>User is successfully logged in</div> : renderForm}
        {isOpen && <Modal setIsOpen={setIsOpen} status={status} />}

      </div>
    </div>
  );
}

export default Login;
