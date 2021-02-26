import api from "../../utls/api";
import { setAlert } from "./alert";
import setAuthToken from "../../utls/setAuthToken";
import jwt from "jwt-decode";

import {
  AUTH_ERROR,
  LOGIN_FAIL,
  LOGIN_SUCCESS,
  LOGOUT,
  REGISTER_FAIL,
  REGISTER_SUCCESS,
  USER_LOADED,
} from "./types";

export const loadUser = () => async (dispatch) => {
  try {
    const x = jwt(localStorage.getItem("token"));
    console.log(x);
    console.log("this is load");
    const res = await api.get("/api/" + x.sub);
    console.log(JSON.stringify(res.data));
    dispatch({
      type: USER_LOADED,
      payload: res.data,
    });
    console.log(JSON.stringify(res.data));
  } catch (err) {
    dispatch({
      type: AUTH_ERROR,
    });
  }
};
export const register = (formData) => async (dispatch) => {
  try {
    const res = await api.post("/api/signup", formData);
    dispatch({
      type: REGISTER_SUCCESS,
      payload: res.data,
    });
    dispatch(loadUser());
    console.log("User Registered Successfully");
  } catch (err) {
    const errors = err.response.data.errors;

    if (errors) {
      errors.forEach((error) => dispatch(setAlert(error.msg, "danger")));
    }

    dispatch({
      type: REGISTER_FAIL,
    });
    //these errors we wamt to display using alert only
  }
};

export const login = (username, password) => async (dispatch) => {
  const body = { username, password };
  try {
    const res = await api.post("/login", body);
    let token = res.headers.authorization.substring(7);
    localStorage.setItem("token", token);

    dispatch({ type: LOGIN_SUCCESS, payload: res.data });
    dispatch(loadUser());
    //dispatch(setAuthToken);
    console.log("login works");
  } catch (err) {
    const errors = err.response.data.errors;
    if (errors) {
      errors.forEach((error) => dispatch(setAlert(error.msg, "danger")));
    }
    dispatch({ type: LOGIN_FAIL });
  }
};

export const logout = () => ({ type: LOGOUT });
