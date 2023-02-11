import { postJson } from 'requests';

import config from "../../../config";

import {
    ERROR_SAVE_BOOK,
    REQUEST_SAVE_BOOK,
    SUCCESS_SAVE_BOOK,
} from '../constants/actionTypes';

const errorSaveBook = () => ({
    type: ERROR_SAVE_BOOK,
});

const requestSaveBook = () => ({
    type: REQUEST_SAVE_BOOK,
});

const successSaveBook = () => ({
    type: SUCCESS_SAVE_BOOK,
});

const saveBook = (book) => {
    const {
        BASE_URL,
    } = config;

    return postJson({
        body: book,
        // url: `http://localhost:8080/books`,
        url: `${BASE_URL}/books`
    });
};

export const fetchSaveBook = (book) => (dispatch) => {
    dispatch(requestSaveBook());

    return saveBook(book)
        .then(() => {
            dispatch(successSaveBook());
        })
        .catch(() => {
            dispatch(errorSaveBook());
        });
};