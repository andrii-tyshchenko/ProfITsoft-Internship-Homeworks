import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import EditBookPage from 'pages/EditBook';
import PageContainer from 'components/PageContainer';

const EditBook = () => (
    <PageAccessValidator> {/*перевіряє, чи є у юзера права доступу до сторінки?*/}
        <PageContainer> {/*розміщує своїх children по центру*/}
            <EditBookPage />
        </PageContainer>
    </PageAccessValidator>
);

export default EditBook;