import { getJson, putJson } from 'requests';

import config from "../../../config";

import {
    ERROR_RECEIVE_BOOK,
    RECEIVE_BOOK,
    REQUEST_BOOK,
    ERROR_UPDATE_BOOK,
    REQUEST_UPDATE_BOOK,
    SUCCESS_UPDATE_BOOK,
} from '../constants/actionTypes';

const errorReceiveBook = () => ({
    type: ERROR_RECEIVE_BOOK,
});

const getBook = (id) => {
    const {
        BASE_URL,
    } = config;

    return getJson({ // робимо звернення до БЕ для отримання книги по id
        // url: 'http://localhost:8080/books/${id}',
        url: `${BASE_URL}/books/${id}`,
    })
    // фейкова відповідь від БЕ:
    // .catch(() => {
    //     const book = {
    //             "id": 1,
    //             "title": "The Shining",
    //             "author": "Stephen King",
    //             "publisherId": 1,
    //             "publisherName": "KSD",
    //             "isbn": "978-966-14-6279-2",
    //             "publishingYear": "2014"
    //     };
    //
    //     return book;
    // });
};

const requestBook = () => ({
    type: REQUEST_BOOK,
});

const receiveBook = (book) => ({
    type: RECEIVE_BOOK,
    book: book,
});

export const fetchBook = (id) => (dispatch) => {
    dispatch(requestBook());

    return getBook(id)
        .then(book => { dispatch(receiveBook(book)) })
        .catch(() => dispatch(errorReceiveBook()));
};

const errorUpdateBook = () => ({
    type: ERROR_UPDATE_BOOK,
});

const requestUpdateBook = () => ({
    type: REQUEST_UPDATE_BOOK,
});

const successUpdateBook = () => ({
    type: SUCCESS_UPDATE_BOOK,
});

const updateBook = (id, book) => {
    const {
        BASE_URL,
    } = config;

    return putJson({
        body: book,
        // url: `http://localhost:8080/books/${id}`,
        url: `${BASE_URL}/books/${id}`
    });
};

export const fetchUpdateBook = (id, book) => (dispatch) => {
    dispatch(requestUpdateBook());

    return updateBook(id, book)
        .then(() => {
            dispatch(successUpdateBook());
        })
        .catch(() => {
            dispatch(errorUpdateBook());
        });
};