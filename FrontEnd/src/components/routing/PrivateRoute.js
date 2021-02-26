import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { Redirect, Route } from "react-router-dom";
import Spinner from "../common/Spinner";

const PrivateRoute = ({
  component: Component,
  auth: { isAuthenticated, loading },
  ...rest
}) => (
  <Route
    {...rest}
    render={(props) =>
      loading ? (
        <Spinner></Spinner>
      ) : isAuthenticated ? (
        <Component {...props} />
      ) : (
        <Redirect to="/login"></Redirect>
      )
    }
  />
);
PrivateRoute.propTypes = {
  isAuthenticated: PropTypes.object,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

const mapDispatchToProps = {};

export default connect(mapStateToProps, mapDispatchToProps)(PrivateRoute);
