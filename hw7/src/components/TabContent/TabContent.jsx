import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
}));

const TabContent = ({
  children,
}) => {
  const classes = getClasses();

  return (
    <div className={classes.container}>
      {children}
    </div>
  )
};


TabContent.propTypes = {
  value: PropTypes.string.isRequired,
};

export default TabContent;