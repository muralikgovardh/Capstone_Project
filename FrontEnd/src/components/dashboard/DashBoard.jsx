import React, { Fragment, useEffect, useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";

import axios from "axios";

import App1 from "./App1";
import { setAlert } from "../../redux/actions/alert";

const Dashboard = ({ auth: { user }, setAlert }) => {
  const [data2, setdata2] = useState({}); // To store formattedAddress
  const [resp, setresp] = useState([]); // To store response from mapmyindia

  // To access mapmyinidia addresses after typing
  const searchElement = (e) => {
    setdata2({ formattedAddress: e.target.value });
    const options = {
      method: "GET",
      url: "/api/places/geocode?",
      params: {
        itemCount: "5",
        address: e.target.value,
      },
      headers: {
        Authorization: "Bearer c0636a41-8943-4415-901f-96db1fe19f30", // Oauth API token to access geocode API
      },
    };
    console.log(e.target.value);
    // To set response when the search bar is not empty
    if (e.target.value !== "") {
      axios(options)
        .then((e) => {
          setresp(e.data.copResults);
        })
        .catch((err) => {
          setresp([]);
        });
    } else {
      console.log("else");
      setresp([]);
    }
  };
  // To select the desired address
  const onClickAddr = (e) => {
    setdata2(e);
    console.log(data2);
    setresp([]);
  };
  // To submit and save the data in database
  const onSubmit = (e) => {
    e.preventDefault();

    console.log(data2);

    axios
      .post("/api/saveaddress", data2) // endpoint for saving the address
      .then((res) => {
        setAlert(res.data, "success");
      })
      .catch((err) => {
        console.log("failed");
        setAlert(err.data, "danger");
      });
  };
  return (
    <Fragment>
      <p className="classf large ">
        <i className="classf " /> Welcome {user && user.name}
      </p>
      <div className="center">
        <center>
          <input
            className="bigblue center"
            placeholder="Search Here"
            value={data2.formattedAddress}
            onChange={searchElement}
          />
          <ul>
            {resp &&
              resp.map((event) => (
                <li
                  className="classf"
                  onClick={() => {
                    onClickAddr(event);
                  }}
                >
                  {" "}
                  {event.formattedAddress}
                </li>
              ))}
          </ul>
          <button onClick={onSubmit}>Save</button>
        </center>
        <App1 />
      </div>
    </Fragment>
  );
};
// To define the param property
Dashboard.propTypes = {
  auth: PropTypes.object.isRequired,
  setAlert: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
  auth: state.auth,
});

const mapDispatchToProps = { setAlert };

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
