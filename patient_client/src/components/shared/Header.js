import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <nav className='container mt-3 navbar navbar-expand-lg navbar-light bg-light'>
      <Link className='navbar-brand' to='/'>
        {'VinBrain'.toUpperCase()}
      </Link>
      <button
        className='navbar-toggler'
        type='button'
        data-toggle='collapse'
        data-target='#navbarNavDropdown'
        aria-controls='navbarNavDropdown'
        aria-expanded='false'
        aria-label='Toggle navigation'
      >
        <span className='navbar-toggler-icon'></span>
      </button>
    </nav>
  );
};

export default Header;
