import React, { Component } from "react";

export default class Footer extends Component {
  render() {
    return (
      <center>
        <footer className="bg-dark text-white mt-5 p-4 center">
          Copyright &copy; {new Date().getFullYear()} Tavant Techologies
        </footer>
      </center>
    );
  }
}
