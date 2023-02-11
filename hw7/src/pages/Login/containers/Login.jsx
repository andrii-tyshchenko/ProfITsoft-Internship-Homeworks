import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AuthorizationForm from 'components/AuthorizationForm';

const getClasses = makeStyles(() => ({
  container: {
    alignItems: 'center',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    width: '100%'
  },
}));

const Login = () => {
  const classes = getClasses();

  return (
    <div className={classes.container}>
      <AuthorizationForm />
    </div>
  )
};

export default Login;