import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { fetchPatients } from '../actions/patientAction';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Pagination from '@material-ui/lab/Pagination';
import Modal from '../components/Modal';
import { Link } from 'react-router-dom';
import api from '../api/patients';

const StyledTableCell = withStyles((theme) => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
  root: {
    '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
    },
  },
}))(TableRow);

const renderItem = (patients) => {
  return patients.map((patient) => (
    <StyledTableRow key={patient.id}>
      <StyledTableCell align='center'>{patient.id}</StyledTableCell>
      <StyledTableCell align='center'>{patient.name}</StyledTableCell>
      <StyledTableCell align='center'>{patient.gender}</StyledTableCell>
      <StyledTableCell align='center'>{patient.age}</StyledTableCell>
      <StyledTableCell align='center'>{patient.email}</StyledTableCell>
      <StyledTableCell align='center'>{patient.phone_number}</StyledTableCell>
      <StyledTableCell align='center'>
        <Link
          className='link'
          to={{
            pathname: `/patient/edit/${patient.id}`,
            state: { patient },
          }}
        >
          Edit
        </Link>{' '}
        |{' '}
        <Link
          to='#'
          className='link'
          data-toggle='modal'
          data-target={`#modalCenter-${patient.id}`}
        >
          Delete
        </Link>
        <Modal patient={patient} />
      </StyledTableCell>
    </StyledTableRow>
  ));
};

const PatientHome = ({ patients, fetchPatients }) => {
  const [pageNumber, setPageNumber] = useState(0);
  const [totalPage, setTotalPage] = useState(2);

  const getPageNumber = async () => {
    const { data } = await api.get('/patients/size');

    setTotalPage(Math.ceil(data / 10));
  };

  const onChangePage = (event, page) => {
    setPageNumber(page - 1);
  };

  useEffect(() => {
    getPageNumber();
  }, []);

  useEffect(() => {
    fetchPatients({ pageNumber, pageSize: 10 });
    return () => {};
  }, [fetchPatients, pageNumber]);

  return (
    <>
      <form className='ui grid form mt-5'>
        <div className='two wide column field'>
          <div className='ui input'>
            <input type='text' placeholder='PatientID' />
          </div>
        </div>
        <div className='three wide column field'>
          <div className='ui input'>
            <input type='text' placeholder='Name' />
          </div>
        </div>
        <div className='two wide column field'>
          <select className='ui fluid dropdown'>
            <option value=''>Gender</option>
            <option value='Male'>Male</option>
            <option value='Female'>Female</option>
          </select>
        </div>
        <div className='two wide column field'>
          <div className='ui input'>
            <input type='number' placeholder='Age' />
          </div>
        </div>
        <div className='three wide column field'>
          <div className='ui input'>
            <input type='email' placeholder='Email' />
          </div>
        </div>
        <div className='three wide column field'>
          <div className='ui input'>
            <input type='text' placeholder='Phone number' />
          </div>
        </div>
        <div className='one wide column'>
          <button className='ui secondary button'>Filter</button>
        </div>
      </form>
      <TableContainer component={Paper} className='mt-3'>
        <Table className='table' aria-label='customized table'>
          <TableHead>
            <TableRow>
              <StyledTableCell align='center'>PatientID</StyledTableCell>
              <StyledTableCell align='center'>Name</StyledTableCell>
              <StyledTableCell align='center'>Gender</StyledTableCell>
              <StyledTableCell align='center'>Age</StyledTableCell>
              <StyledTableCell align='center'>Email</StyledTableCell>
              <StyledTableCell align='center'>Phone number</StyledTableCell>
              <StyledTableCell align='center'></StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>{renderItem(patients)}</TableBody>
        </Table>
      </TableContainer>

      <div className='ui grid mt-2'>
        <div className='five wide column'>
          <button className='ui secondary button' variant='contained'>
            <Link className='link-create' to='/patient/create'>
              Create Patient
            </Link>
          </button>
        </div>
        <div className='eleven wide column'>
          <Pagination
            onChange={onChangePage}
            count={totalPage}
            variant='outlined'
            shape='rounded'
          />
        </div>
      </div>
    </>
  );
};

const mapStateToProps = (state) => {
  return {
    patients: Object.values(state.patients),
  };
};

const mapDispatchToProps = (dispatch, props) => {
  return {
    fetchPatients: (pagination) => dispatch(fetchPatients(pagination)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(PatientHome);
