import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { useSelector } from 'react-redux';

import * as PAGES from 'constants/pages';
import useChangePage from 'hooks/useChangePage';
import useAccessValidate from 'hooks/useAccessValidate';
import useLocationSearch from 'hooks/useLocationSearch';

const initialState = {
  isValid: false,
};

const PageAccessValidator = ({
  neededAuthorities = [],
  children,
}) => {
  const [state, setState] = useState(initialState);
  const changePage = useChangePage();
  const location = useLocation();
  const locationSearch = useLocationSearch();
  const user = useSelector(({ user }) => user);
  const hasAuthAccess = useAccessValidate({
    ownedAuthorities: user.authorities,
    neededAuthorities,
  });

  useEffect(() => {
    if (!user.isFetchingUser) {
      if (!user.isAuthorized) {
        changePage({
          locationSearch: {
            ...locationSearch,
            redirectPathname: location.pathname,
            redirectLocationSearch: JSON.stringify(locationSearch),
          },
          path: `/${PAGES.LOGIN}`,
        });
      } else if (!hasAuthAccess) {
        changePage({
          locationSearch,
          path: `/${PAGES.INITIAL}`,
        });
      } else {
        setState(prevState => ({
          ...prevState,
          isValid: true,
        }));
      }
    }
  }, [user.isFetchingUser, user.isAuthorized, hasAuthAccess]);

  return state.isValid ? children : null;
};

export default PageAccessValidator;