import React, { useMemo } from 'react';
import { IntlProvider as ReactIntlProvider } from 'react-intl';

import useLocationSearch from 'hooks/useLocationSearch';
import getMessages from 'intl';

const IntlProvider = ({ children }) => {
  const {
    lang,
  } = useLocationSearch();

  const messages = useMemo(() => getMessages(lang), [lang]);

  return (
    <ReactIntlProvider locale={lang} messages={messages}>
      {children}
    </ReactIntlProvider>
  )
};

export default IntlProvider;