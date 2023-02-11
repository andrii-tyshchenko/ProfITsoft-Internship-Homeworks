import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import BooksPage from 'pages/Books';
import PageContainer from 'components/PageContainer';

const Books = () => (
  <PageAccessValidator> {/*перевіряє, чи є у юзера права доступу до сторінки?*/}
    <PageContainer> {/*розміщує своїх children по центру*/}
      <BooksPage />
    </PageContainer>
  </PageAccessValidator>
);

export default Books;