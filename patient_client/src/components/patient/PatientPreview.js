import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { connect } from 'react-redux';
import { createPatient, editPatient } from '../../actions/patientAction';

const PatientPreview = ({ location, createPatient, editPatient }) => {
  const [error, setError] = useState(false);
  let patient = location.state;
  const position = patient.position;
  delete patient.position;

  let history = useHistory();

  const handleSave = async () => {
    if (patient.id !== undefined) {
      const response = await editPatient(patient.id, patient);
      if (response.id) {
        history.push('/patient/success', { id: patient.id, position });
        setError(false);
      } else {
        setError(true);
      }
    } else {
      createPatient(patient);
      history.push('/patient/success', { id: patient.id, position });
    }
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
            <button
              className='ui secondary button'
              disabled={error === true ? true : false}
              onClick={handleSave}
            >
              Save
            </button>
          </div>
        </div>
        {error === true ? (
          <div className='mt-5 text-danger'>Opps, Something went wrong!</div>
        ) : null}
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
