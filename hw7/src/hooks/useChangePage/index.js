import { useHistory } from 'react-router-dom';

const useChangePage = () => {
  const history = useHistory();

  return ({
    locationSearch = {},
    path,
  }) => history.push({
    pathname: path
      ? path
      : history.location.pathname,
    search: `?${new URLSearchParams(locationSearch).toString()}`,
  });
};

export default useChangePage;