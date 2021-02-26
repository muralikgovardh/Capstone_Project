import React, { Fragment, useState } from "react";
import { connect } from "react-redux";
import { Link, Redirect } from "react-router-dom";
import PropTypes from "prop-types";

import { register } from "../../redux/actions/auth";
import { setAlert } from "../../redux/actions/alert";

const Register3 = ({ register, setAlert, isAuthenticated }) => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    password2: "",
  }); // state for formdat

  const { name, email, password, password2 } = formData;

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });
  const onSubmit = async (e) => {
    e.preventDefault();
    if (password !== password2) {
      console.log("password donont match");
      setAlert("password does not match ", "danger");
      //  setAlert('Passwords do not match', 'danger');
    } else if (name == null) {
      setAlert("No name entered", "danger");
    } else {
      register({ name, email, password });
    }
  };
  if (isAuthenticated) {
    return <Redirect to="/dashboard"></Redirect>;
  }
  return (
    <Fragment>
      <center>
        <h1 className="large text-primary classf">Sign Up</h1>
        <p className="lead classf">
          <i className="fas fa-user classf" /> Create Your Account
        </p>
        <form className="form" onSubmit={onSubmit}>
          <div className="form-group">
            <input
              type="text"
              placeholder="Name"
              name="name"
              value={name}
              onChange={onChange}
            />
          </div>
          <div className="form-group">
            <input
              type="email"
              placeholder="Email Address"
              name="email"
              value={email}
              onChange={onChange}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              placeholder="Password"
              name="password"
              value={password}
              onChange={onChange}
            />
          </div>
          <div className="form-group">
            <input
              type="password"
              placeholder="Confirm Password"
              name="password2"
              value={password2}
              onChange={onChange}
            />
          </div>
          <input type="submit" className="btn btn-primary" value="Register" />
        </form>
        <p className="my-1 classg">
          Already have an account?{" "}
          <Link className="classg" to="/login">
            Sign In
          </Link>
        </p>
      </center>
    </Fragment>
  );
};

Register3.propTypes = {
  register: PropTypes.func.isRequired,
  setAlert: PropTypes.func.isRequired,
  isAuthenticated: PropTypes.bool,
};

const mapStateToProps = (state) => ({
  isAuthenticated: state.auth.isAuthenticated,
});

const mapDispatchToProps = { register, setAlert };

export default connect(mapStateToProps, mapDispatchToProps)(Register3);
