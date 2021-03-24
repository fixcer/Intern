import React from 'react';
import { useLocation } from 'react-router-dom';
import PatientForm from '../components/patient/PatientForm';

const PatientEdit = () => {
  let location = useLocation();
  return <PatientForm title='Edit patient' patient={location.state.patient} />;
};

export default PatientEdit;
