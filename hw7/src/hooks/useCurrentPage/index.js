import { useLocation } from 'react-router-dom';

const useCurrentPage = () => {
  const {
    pathname,
  } = useLocation();

  return pathname.substring(pathname.indexOf('/') + 1);
};

export default useCurrentPage;