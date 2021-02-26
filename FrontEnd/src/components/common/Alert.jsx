import React from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";

const Alert = ({ alerts }) => {
  console.log(JSON.stringify(alerts));
  return (
    alerts !== null &&
    alerts.map((alert) => (
      <div key={alert.id} className={`alert alert-${alert.alertType}`}>
        {alert.msg}
      </div>
    ))
  );
};

Alert.prototypes = {
  alerts: PropTypes.array.isRequired,
};

const mapStateToProps = (state) => ({ alerts: state.alert });

export default connect(mapStateToProps)(Alert);
