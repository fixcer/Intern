import React from 'react';
import PatientForm from '../components/patient/PatientForm';

const PatientCreate = ({ location }) => {
  return (
    <PatientForm
      title='Create new patient'
      patient={{}}
      position={location.state}
    />
  );
};

export default PatientCreate;
