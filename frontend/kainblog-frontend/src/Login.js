
import React, { useState } from "react";

import "./Login.css";

function Login() {

  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);

  const database = [
    {
      username: "user1",
      password: "pass1"
    },
    {
      username: "user2",
      password: "pass2"
    }
  ];

  const errors = {
    user: "invalid username",
    pass: "invalid password"
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    var loginform = document.forms[0];

    var loginuser = loginform.user;
    var pass = loginform.pass;

    const userData = database.find((user) => user.username === loginuser.value);

    if (userData) {
      if (userData.password !== pass.value) {
        setErrorMessages({ name: "pass", message: errors.pass });
      } else {
        setIsSubmitted(true);
      }
    } else {
      setErrorMessages({ name: "user", message: errors.user });
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
            <button type="submit" class="button">Login</button>
        </div>
        </form>
    </div>
    
  );

  return (
    <dic>
      <div>
        <br/><br/>
        <div>Sign In</div>
        {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
      </div>
    </dic>
  );
}

export default Login;
