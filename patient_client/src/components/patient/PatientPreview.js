import React from 'react';
import { useHistory } from 'react-router-dom';
import { connect } from 'react-redux';
import { createPatient, editPatient } from '../../actions/patientAction';

const PatientPreview = ({ location, createPatient, editPatient }) => {
  const patient = location.state;

  let history = useHistory();

  const handleSave = async () => {
    patient.id !== undefined
      ? editPatient(patient.id, patient)
      : createPatient(patient);
    history.push('/patient/success', patient.id);
  };

  return (
    <>
      <div className='d-flex justify-content-center title'>
        {patient.id !== undefined
          ? 'Edit patient confirm'
          : 'Create new patient confirm'}
      </div>

      <div className='container mt-5'>
        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Name*</label>
          </div>
          <div className='three wide column'>{patient.name}</div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Gender*</label>
          </div>
          <div className='three wide column'>{patient.gender}</div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Age*</label>
          </div>
          <div className='three wide column'>{patient.age}</div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Email</label>
          </div>
          <div className='three wide column'>{patient.email}</div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Phone number*</label>
          </div>
          <div className='three wide column'>{patient.phone_number}</div>
        </div>

        <div className='ui grid centered mt-3'>
          <div className='four wide column'>
            <button className='ui button' onClick={() => history.goBack()}>
              Back
            </button>
          </div>
          <div className='three wide column'>
            <button className='ui secondary button' onClick={handleSave}>
              Save
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

const mapStateToProps = (state) => {
  return {};
};

const mapDispatchToProps = (dispatch, props) => {
  return {
    createPatient: (item) => dispatch(createPatient(item)),
    editPatient: (id, item) => dispatch(editPatient(id, item)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(PatientPreview);
