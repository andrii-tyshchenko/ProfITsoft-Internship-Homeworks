import classNames from 'classnames';
import { makeStyles } from '@material-ui/core/styles';
import PropTypes from 'prop-types';
import React from 'react';
import { Link as InternalLink } from 'react-router-dom';
import ExternalLink from '@material-ui/core/Link';

const getClasses = makeStyles(() => ({
  noUnderline: {
    textDecoration: 'none',
  },
}));

const Link = ({
  children,
  href,
  to,
  underline = false,
}) => {
  const classes = getClasses();
  const LinkComponent = href
   ? ExternalLink
   : InternalLink;

  return (
    <LinkComponent
      className={classNames(
        !underline && classes.noUnderline,
      )}
      href={href}
      to={to}
    >
      {children}
    </LinkComponent>
  );
};

Link.propTypes = {
  children: PropTypes.node,
  href: PropTypes.string,
  to: PropTypes.oneOfType([PropTypes.object, PropTypes.func]),
  underline: PropTypes.bool,
};

export default Link