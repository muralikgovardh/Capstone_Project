import React, { Fragment } from "react";
import spinner from "../../assets/img/spinner.gif";
function Spinner() {
  return (
    <Fragment>
      <img
        src={spinner}
        style={{ width: "200px", margin: "auto", display: "block" }}
        alt="Loading..."
      ></img>
    </Fragment>
  );
}

export default Spinner;
