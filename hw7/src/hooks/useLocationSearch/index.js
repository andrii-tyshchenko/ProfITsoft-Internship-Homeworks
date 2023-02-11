import { useMemo } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import * as LANGUAGES from 'constants/languages';

const DEFAULT_LOCATION_SEARCH = {
  lang: LANGUAGES.en,
};

const searchToObject = (search) => {
  const params = new URLSearchParams(search);
  let result = {};
  for (let param of params) { // each 'entry' is a [key, value] tuple
    const [key, value] = param;
    result[key] = value;
  }

  return result;
};

const getInvalidLocationSearchParams = (locationSearch) => {
  const {
    lang,
  } = locationSearch;
  const invalidParams = [];

  if (!Object.keys(LANGUAGES).includes(lang)) {
    invalidParams.push('lang');
  }

  return invalidParams;
};

const getValidLocationSearch = (inputLocationSearch, invalidLocationSearchParams) => {
  const validLocationSearch = {
    ...DEFAULT_LOCATION_SEARCH,
    ...(inputLocationSearch || {}),
  };
  invalidLocationSearchParams
    .forEach(invalidParamKey => validLocationSearch[invalidParamKey] = DEFAULT_LOCATION_SEARCH[invalidParamKey]);

  return validLocationSearch;
};

const useLocationSearch = () => {
  const {
    search,
  } = useLocation();
  const history = useHistory();

  return useMemo(() => {
    const locationSearch = searchToObject(search);
    const invalidLocationSearchParams = getInvalidLocationSearchParams(locationSearch);
    const validLocationSearch = getValidLocationSearch(locationSearch, invalidLocationSearchParams);

    if (invalidLocationSearchParams.length) {
      history.replace({
        pathname: history.location.pathname,
        search: `?${new URLSearchParams(validLocationSearch).toString()}`,
      });
    }

    return validLocationSearch;
  }, [history.location.pathname, search]);
};

export default useLocationSearch;