import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';

const PatientForm = ({ title, patient, position }) => {
  const [gender, setGender] = useState('Male');
  const [tickName, setTickName] = useState('display');
  const [tickAge, setTickAge] = useState('display');
  const [tickPhone, setTickPhone] = useState('display');

  const { register, handleSubmit, errors } = useForm({
    mode: 'all',
    defaultValues: patient,
  });

  let history = useHistory();

  const onSubmit = (data) => {
    data['gender'] = gender;
    data['id'] = patient.id;
    data['position'] = position;
    history.push('/patient/preview', data);
  };

  return (
    <>
      <div className='d-flex justify-content-center title'>{title}</div>

      <form
        className='container mt-5 ui form'
        onSubmit={handleSubmit(onSubmit)}
      >
        {patient.id ? (
          <div className='ui grid field'>
            <div className='seven wide column'>
              <label>PatientID</label>
            </div>
            <div className='three wide column'>
              <input disabled placeholder={patient.id} />
            </div>
          </div>
        ) : null}
        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Name</label>
          </div>
          <div className='three wide column'>
            <input
              autoComplete='off'
              name='name'
              ref={register({ required: true, minLength: 5, maxLength: 32 })}
              onBlur={() => {
                setTickName('');
              }}
            />
          </div>
          <div className='five wide column tick'>
            {!errors.name ? <p className={tickName}>✅</p> : null}
            {errors.name && <p>❌</p>}
          </div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Gender</label>
          </div>
          <div className='three wide column field'>
            <select
              defaultValue={patient.id ? patient.gender : 'Male'}
              name='gender'
              onChange={(e) => setGender(e.target.value)}
            >
              <option value='Male'>Male</option>
              <option value='Female'>Female</option>
            </select>
          </div>
          <div className='five wide column tick'></div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Age</label>
          </div>
          <div className='three wide column'>
            <input
              type='number'
              autoComplete='off'
              name='age'
              ref={register({
                required: true,
                pattern: {
                  value: /^\d+$/,
                },
              })}
              onBlur={() => {
                setTickAge('');
              }}
            />
          </div>
          <div className='five wide column tick'>
            {!errors.age ? <p className={tickAge}>✅</p> : null}
            {errors.age && <p>❌</p>}
          </div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Email</label>
          </div>
          <div className='three wide column'>
            <input
              name='email'
              type='email'
              autoComplete='off'
              ref={register}
            />
          </div>
        </div>

        <div className='ui grid'>
          <div className='seven wide column'>
            <label>Phone number</label>
          </div>
          <div className='three wide column'>
            <input
              type='text'
              autoComplete='off'
              name='phone_number'
              ref={register({ required: true, minLength: 10, maxLength: 11 })}
              onBlur={() => {
                setTickPhone('');
              }}
            />
          </div>
          <div className='five wide column tick'>
            {!errors.phone_number ? <p className={tickPhone}>✅</p> : null}
            {errors.phone_number && <p>❌</p>}
          </div>
        </div>

        <div className='ui grid centered mt-3'>
          <div className='four wide column'>
            <button
              className='ui button'
              onClick={() => history.goBack()}
              type='cancel'
            >
              Cancel
            </button>
          </div>
          <div className='three wide column'>
            <button className='ui secondary button' type='submit'>
              Next
            </button>
          </div>
        </div>
      </form>
    </>
  );
};

export default PatientForm;
