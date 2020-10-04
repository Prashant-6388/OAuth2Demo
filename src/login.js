import React, { Component } from "react";
import axios from "axios";

class login extends Component {
  constructor() {
    super();
    this.handleFormSubmit = this.handleFormSubmit.bind(this);
  }

  handleFormSubmit = event => {
    event.preventDefault();
    axios({
        method: 'post',
        url: 'http://localhost:8080/oauth/token?username=prashant&password=prashant123&grant_type=password',
        headers : {
            //this header is taken directly from code generated from postman, username:test and password:temp 
            'Authorization': 'Basic dGVzdDp0ZW1w'
            
        }
    }).then(res => {
        console.log(res);
        var token = res.data.access_token;
        console.log("token = "+token);
        //get resouces with token
        axios({
            method: 'post',
            url: 'http://localhost:8080/api/users/me',
            headers : {
                'Authorization': 'Bearer '+token
            }
        }).then(res => {
            console.log(res);
        });
    });
  };

  handleDashboard() {
    axios.get("http://localhost:3000/dashboard").then(res => {
      if (res.data === "success") {
        this.props.history.push("/dashboard");
      } else {
        alert("Authentication failure");
      }
    });
  }

  render() {
    return (
      <div>
        <div class="wrapper">
          <form class="form-signin" onSubmit={this.handleFormSubmit}>
            <h2 class="form-signin-heading">Please login</h2>
            
            <button class="btn btn-lg btn-primary btn-block" type="submit">
              Login
            </button>
          </form>
        </div>
      </div>
    );
  }
}
export default login;