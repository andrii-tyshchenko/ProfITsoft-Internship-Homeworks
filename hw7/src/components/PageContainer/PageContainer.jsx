import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    justifyContent: 'center',
  },
  content: {
    display: 'flex',
    width: '1080px',
  },
}));

const PageContainer = ({
  children,
}) => {
  const classes = getClasses();

  return (
    <div className={classes.container}>
      <div className={classes.content}>
        {children}
      </div>
    </div>
  );
};

export default PageContainer;