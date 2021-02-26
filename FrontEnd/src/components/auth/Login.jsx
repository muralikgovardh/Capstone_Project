import React, { Fragment, useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Link, Redirect } from "react-router-dom";
import { login } from "../../redux/actions/auth";
import { setAlert } from "../../redux/actions/alert";

const Login = ({ login, isAuthenticated, setAlert }) => {
  const [formData, setFormData] = useState({ username: "", password: "" }); // assigning state
  const { username, password } = formData;

  const onChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
  const onSubmit = (e) => {
    e.preventDefault();
    if (password == null) {
      setAlert("Password must not be null", "danger");
    } else {
      login(username, password);
    }
  };
  if (isAuthenticated) {
    return <Redirect to="/dashboard"></Redirect>;
  }
  return (
    <Fragment>
      <center>
        <h1 className="large text-primary classf">Sign In</h1>
        <p className="lead classf">
          <i className="fas fa-user classf" /> Sign Into Your Account
        </p>
        <form className="form" onSubmit={onSubmit}>
          <div className="form-group">
            <input
              type="text"
              placeholder="Enter the User Name "
              name="username"
              value={username}
              onChange={onChange}
              required
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              placeholder="Enter the Password"
              name="password"
              value={password}
              onChange={onChange}
              minLength="6"
            />
          </div>
          <input type="submit" className="btn btn-primary " value="Login" />
        </form>
        <p className="my-1 classg">
          Don't have an account?{" "}
          <Link className="classg" to="/register">
            Sign Up
          </Link>
        </p>
      </center>
    </Fragment>
  );
};

Login.propTypes = {
  login: PropTypes.func.isRequired,
  isAuthenticated: PropTypes.bool,
  setAlert: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated,
});

const mapDispatchToProps = { login, setAlert };

export default connect(mapStateToProps, mapDispatchToProps)(Login);
