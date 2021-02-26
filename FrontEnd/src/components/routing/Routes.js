import React from "react";
import PropTypes from "prop-types";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Alert from "../common/Alert";
import Register from "../auth/Register";
import Login from "../auth/Login";

import DashBoard from "../dashboard/DashBoard";
import PrivateRoute from "./PrivateRoute";

const Routes = (props) => {
  return (
    <section>
      <Alert />
      <Switch>
        <Route exact path="/register" component={Register}></Route>
        <Route exact path="/login" component={Login}></Route>
        <Route exact path="/alert" component={Alert}></Route>
        <PrivateRoute
          exact
          path="/dashboard"
          component={DashBoard}
        ></PrivateRoute>
      </Switch>
    </section>
  );
};

export default Routes;
