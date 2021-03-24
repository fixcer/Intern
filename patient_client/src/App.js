import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import Header from './components/shared/Header';
import Routes from './Routes';

const App = () => {
  return (
    <Router>
      <Header />
      <Routes />
    </Router>
  );
};

export default App;
