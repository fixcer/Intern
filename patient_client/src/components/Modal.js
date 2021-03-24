import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { deletePatient } from '../actions/patientAction';

const Modal = ({ patient, deletePatient }) => {
  const handleDelete = () => {
    deletePatient(patient.id);
  };

  return (
    <div
      className='modal fade'
      id={`modalCenter-${patient.id}`}
      tabIndex='-1'
      role='dialog'
      aria-labelledby='modalCenterTitle'
      aria-hidden='true'
    >
      <div className='modal-dialog modal-dialog-centered' role='document'>
        <div className='modal-content'>
          <div className='modal-header'>
            <h5 className='modal-title' id='modalLongTitle'>
              Confirm
            </h5>
            <button
              type='button'
              className='close'
              data-dismiss='modal'
              aria-label='Close'
            >
              <span aria-hidden='true'>&times;</span>
            </button>
          </div>
          <div className='modal-body text-left'>{`Do you want to delete patient with id ${patient.id}?`}</div>
          <div className='modal-footer'>
            <Link to='/' className='ui button' data-dismiss='modal'>
              Cancel
            </Link>

            <button
              className='ui secondary button'
              data-dismiss='modal'
              onClick={handleDelete}
            >
              Yes
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (state, ownProps) => {
  return {
    // patient: state.patients[ownProps.match.params.patient_id],
  };
};

const mapDispatchToProps = (dispatch, props) => {
  return {
    deletePatient: (id) => dispatch(deletePatient(id)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(Modal);
