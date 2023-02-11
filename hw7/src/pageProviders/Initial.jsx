import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import InitialPage from 'pages/Initial';
import PageContainer from 'components/PageContainer';

const Initial = () => (
  <PageAccessValidator> {/*перевіряє, чи є у юзера права доступу до сторінки?*/}
    <PageContainer> {/*розміщує своїх children по центру*/}
      <InitialPage />
    </PageContainer>
  </PageAccessValidator>
);

export default Initial;