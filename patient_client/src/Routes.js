import React from 'react';
import { Switch, Route } from 'react-router-dom';
import PatientHome from './pages/PatientHome';
import PatientCreate from './pages/PatientCreate';
import PatientEdit from './pages/PatientEdit';
import PatientPreview from './components/patient/PatientPreview';
import PatientSuccess from './components/patient/PatientSuccess';
import PatientError from './components/patient/PatientError';

const Routes = () => {
  return (
    <div className='container'>
      <Switch>
        <Route exact path='/' component={PatientHome} />
        <Route path='/patient/create' component={PatientCreate} />
        <Route path='/patient/edit/:id' component={PatientEdit} />
        <Route path='/patient/preview' component={PatientPreview} />
        <Route path='/patient/success' component={PatientSuccess} />
        <Route path='/patient/error' component={PatientError} />
      </Switch>
    </div>
  );
};

export default Routes;
