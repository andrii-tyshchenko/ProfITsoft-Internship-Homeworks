import { useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import useChangePage from 'hooks/useChangePage';
import LoginPage from 'pages/Login';
import PageContainer from 'components/PageContainer';
import useLocationSearch from 'hooks/useLocationSearch';
import * as PAGES from 'constants/pages';

const Login = () => {
  const locationSearch = useLocationSearch();
  const user = useSelector(({ user }) => user);
  const changePage = useChangePage();

  useEffect(() => {
    if (user.isAuthorized) {
      changePage({
        locationSearch: locationSearch.redirectLocationSearch
          ? JSON.parse(locationSearch.redirectLocationSearch)
          : locationSearch,
        path: locationSearch.redirectPathname || `/${PAGES.INITIAL}`,
      });
    }
  }, [user.isAuthorized]);

  return (
    user.isAuthorized
    ? null
    : (
      <PageContainer>
        <LoginPage />
      </PageContainer>
    )
  );
};

export default Login;