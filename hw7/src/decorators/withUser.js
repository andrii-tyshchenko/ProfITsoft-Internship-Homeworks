import { useSelector } from 'react-redux';
import React from 'react';

const withUser = Component => (props) => {
  const user = useSelector(({ user }) => user);

  return (
    <Component
      user={user}
      {...props}
    />
  );
};

export default withUser;