import { useSelector } from 'react-redux';
import React from 'react';

const withAuthorities = Component => (props) => {
  const authorities = useSelector(({ user }) => user.authorities);
  return (
    <Component
      authorities={authorities}
      {...props}
    />
  );
};

export default withAuthorities;