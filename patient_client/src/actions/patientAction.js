import patients from '../api/patients';

import {
  CREATE_PATIENT,
  FETCH_PATIENTS,
  FETCH_PATIENT,
  EDIT_PATIENT,
  DELETE_PATIENT,
} from './types';

export const createPatient = (formValues) => async (dispatch) => {
  const { data } = await patients.post('/patients', formValues);

  dispatch({ type: CREATE_PATIENT, payload: data });
};

export const fetchPatients = (pagination) => async (dispatch) => {
  const { data } = await patients.get('/patients', { params: pagination });
  dispatch({ type: FETCH_PATIENTS, payload: data.content });
};

export const fetchPatient = (id) => async (dispatch) => {
  const { data } = await patients.get(`/patients/${id}`);

  dispatch({ type: FETCH_PATIENT, payload: data });
};

export const editPatient = (id, formValues) => async (dispatch) => {
  return await patients
    .patch(`/patients/${id}`, formValues)
    .then((v) => {
      dispatch({ type: EDIT_PATIENT, payload: v.data });
      return v.data;
    })
    .catch((error) => {
      dispatch({ type: EDIT_PATIENT, payload: formValues });
      return error.response;
    });
};

export const deletePatient = (id) => async (dispatch) => {
  await patients.delete(`/patients/${id}`);

  dispatch({ type: DELETE_PATIENT, payload: id });
};
