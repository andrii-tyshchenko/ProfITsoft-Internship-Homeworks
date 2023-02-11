import { getJson, deleteJson } from 'requests';

import config from "../../../config";

import {
    ERROR_RECEIVE_BOOKS,
    RECEIVE_BOOKS,
    REQUEST_BOOKS,
    ERROR_DELETE_BOOK,
    SUCCESS_DELETE_BOOK,
    REQUEST_DELETE_BOOK,
} from '../constants/actionTypes';

const errorReceiveBooks = () => ({
    type: ERROR_RECEIVE_BOOKS,
});

const getBooks = () => {
    const {
        BASE_URL,
    } = config;

    return getJson({ // робимо звернення до БЕ для отримання списку книг
        // url: 'http://localhost:8080/books',
        url: `${BASE_URL}/books`,
    })
    // фейкова відповідь від БЕ:
    // .catch(() => {
    //     const books = [
    //         {
    //             "id": 1,
    //             "title": "The Shining",
    //             "author": "Stephen King",
    //             "publisherId": 1,
    //             "publisherName": "KSD",
    //             "isbn": "978-966-14-6279-2",
    //             "publishingYear": "2014"
    //         },
    //         {
    //             "id": 2,
    //             "title": "Complete Collection of Works. Vol.1",
    //             "author": "H.P.Lovecraft",
    //             "publisherId": 2,
    //             "publisherName": "Zhupansky Publisher",
    //             "isbn": "978-966-2355-69-7",
    //             "publishingYear": "2016"
    //         },
    //     ];
    //
    //     return books;
    // });
};

const requestBooks = () => ({
    type: REQUEST_BOOKS,
});

const receiveBooks = (books) => ({
    type: RECEIVE_BOOKS,
    books: books,
});

export const fetchBooks = () => (dispatch) => {
    dispatch(requestBooks());

    return getBooks({
      dispatch,
    }).then(books => dispatch(receiveBooks(books)))
      .catch(() => dispatch(errorReceiveBooks()));
};

const errorDeleteBook = () => ({
    type: ERROR_DELETE_BOOK,
});

const requestDeleteBook = () => ({
    type: REQUEST_DELETE_BOOK,
});

const successDeleteBook = () => ({
    type: SUCCESS_DELETE_BOOK,
});

const deleteBook = (id) => {
    const {
        BASE_URL,
    } = config;

    return deleteJson({
        // url: `http://localhost:8080/books/${id}`,
        url: `${BASE_URL}/books/${id}`
    }).catch();
};

export const fetchDeleteBook = (id) => (dispatch) => {
    dispatch(requestDeleteBook());

    return deleteBook(id)
        .then(() => {
            dispatch(successDeleteBook());
        })
        .catch(() => dispatch(errorDeleteBook()));
};