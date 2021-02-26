import { createStore, applyMiddleware } from "redux";
//create store to create the redux store.
//applyMiddleware :to apply the thunk

import { composeWithDevTools } from "redux-devtools-extension";
//will provide the access to ur redux devtool exten from chrome/firefox

import thunk from "redux-thunk";
import setAuthToken from "../../utls/setAuthToken";
// this is ur middleware will help u to connect to store to manipulate the

import rootReducer from "../reducers";

const initialState = {};
const middleware = [thunk];
const store = createStore(
  rootReducer,
  initialState,
  composeWithDevTools(applyMiddleware(...middleware))
);

let currentState = store.getState();
store.subscribe(() => {
  let previousState = currentState;
  currentState = store.getState();

  if (previousState.auth.token !== currentState.auth.token) {
    const token = currentState.auth.token;
    setAuthToken(token);
  }
});

export default store;
