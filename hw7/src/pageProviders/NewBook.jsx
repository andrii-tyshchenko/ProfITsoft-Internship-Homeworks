import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import NewBookPage from 'pages/NewBook';
import PageContainer from 'components/PageContainer';

const NewBook = () => (
    <PageAccessValidator> {/*перевіряє, чи є у юзера права доступу до сторінки?*/}
        <PageContainer> {/*розміщує своїх children по центру*/}
            <NewBookPage />
        </PageContainer>
    </PageAccessValidator>
);

export default NewBook;