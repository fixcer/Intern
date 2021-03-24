import React from 'react';
import { useHistory } from 'react-router-dom';

const PatientError = () => {
  let history = useHistory();

  const handleClick = () => {
    history.push('/');
  };

  return (
    <div className='container mt-5'>
      <div className='d-flex justify-content-center'>
        <div className='title'>Something went wrong!</div>
      </div>

      <div className='d-flex justify-content-center mt-5'>
        <button className='ui secondary button' onClick={handleClick}>
          Go Home
        </button>
      </div>
    </div>
  );
};

export default PatientError;
