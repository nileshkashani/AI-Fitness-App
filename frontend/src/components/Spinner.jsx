import React from 'react';
import './Spinner.css';

const Spinner = () => {
  return (
    <div className="spinner-container">
      <div className="loader"></div>
      <span className="loading-text">Loading...</span>
    </div>
  );
};

export default Spinner;
