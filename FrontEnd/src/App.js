import logo from "./logo.svg";
import "./App.css";
import Navbar from "./components/layout/Navbar";
import Landing from "./components/layout/Landing";
import Footer from "./components/layout/Footer";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Routes from "./components/routing/Routes";
import { Provider } from "react-redux";
import store from "./redux/store";
import setAuthToken from "../src/utls/setAuthToken";
import { useEffect } from "react";
import { loadUser } from "./redux/actions/auth";

function App() {
  useEffect(() => {
    if (localStorage.token) {
      setAuthToken(localStorage.getItem("token"));
    }
    store.dispatch(loadUser());
  }, []);

  return (
    <Provider store={store}>
      <div className="App">
        <Router>
          <Navbar />
          <br></br>
          <br></br>
          <br></br>
          <Switch>
            <Route exact path="/" component={Landing}></Route>
            <Route component={Routes}></Route>
          </Switch>
        </Router>
        <Footer />
      </div>
    </Provider>
  );
}

export default App;
