import patients from '../api/patients';

import {
  CREATE_PATIENT,
  FETCH_PATIENTS,
  FETCH_PATIENT,
  EDIT_PATIENT,
  DELETE_PATIENT,
} from './types';

export const createPatient = (formValues) => async (dispatch) => {
  const { data } = await patients.post('/patient', formValues);

  dispatch({ type: CREATE_PATIENT, payload: data });
};

export const fetchPatients = (pagination) => async (dispatch) => {
  const { data } = await patients.get('/patients', { params: pagination });
  dispatch({ type: FETCH_PATIENTS, payload: data.content });
};

export const fetchPatient = (id) => async (dispatch) => {
  const { data } = await patients.get(`/patient/${id}`);

  dispatch({ type: FETCH_PATIENT, payload: data });
};

export const editPatient = (id, formValues) => async (dispatch) => {
  console.log(formValues);
  const { data } = await patients.patch(`/patient/${id}`, formValues);

  dispatch({ type: EDIT_PATIENT, payload: data });
};

export const deletePatient = (id) => async (dispatch) => {
  await patients.delete(`/patient/${id}`);

  dispatch({ type: DELETE_PATIENT, payload: id });
};
