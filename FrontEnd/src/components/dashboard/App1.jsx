import React, { useState } from "react";
import * as XLSX from "xlsx";
import DataTable from "react-data-table-component";
import axios from "axios";
import { setAlert } from "../../redux/actions/alert";
import PropTypes from "prop-types";
import { connect } from "react-redux";

function App1({ setAlert }) {
  const [file, setfile] = useState([]);

  // Handle input transfer via api endpoint
  const upload = () => {
    const formData = new FormData();
    console.log(file.name);

    // storing input in formdata
    formData.append("file", file, "file.csv");
    // calling the http endpoint to send the input file
    return axios
      .post("/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((e) => {
        console.log(e);
        setAlert(e.data.message, "success"); // Displaying the success response
      })
      .catch((err) => {
        setAlert(err.message, "danger"); // Displaying the error response
      });
  };

  // handle file upload
  // set input file data
  const handleFileUpload = (e) => {
    const file1 = e.target.files[0];

    setfile(file1);
  };
  return (
    <div>
      <center className="classf">
        <input
          className="btn btn-success"
          type="file"
          name="file"
          accept=".csv"
          onChange={handleFileUpload}
        />

        <button className="btn btn-success" onClick={upload}>
          Upload
        </button>
      </center>
    </div>
  );
}

App1.propTypes = {
  setAlert: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({});

const mapDispatchToProps = { setAlert };

export default connect(mapStateToProps, mapDispatchToProps)(App1);
