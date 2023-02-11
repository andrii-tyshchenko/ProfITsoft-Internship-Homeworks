import {
  ERROR_RECEIVE_BOOKS,
  RECEIVE_BOOKS,
  REQUEST_BOOKS,
} from '../constants/actionTypes';

const initialState = {
  books: [],
  isFetchingBooks: false,
  isFailedFetchBooks: false,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ERROR_RECEIVE_BOOKS: {
      return {
        ...state,
        isFailedFetchBooks: true,
        isFetchingBooks: false,
      };
    }

    case RECEIVE_BOOKS: {
      return {
        ...state,
        books: action.books,
        isFailedFetchBooks: false,
        isFetchingBooks: false,
      }
    }

    case REQUEST_BOOKS: {
      return {
        ...state,
        isFailedFetchBooks: false,
        isFetchingBooks: true,
      };
    }

    default: return state;
  }
}