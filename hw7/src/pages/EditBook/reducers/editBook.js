import {
  ERROR_RECEIVE_BOOK,
  RECEIVE_BOOK,
  REQUEST_BOOK,
  ERROR_UPDATE_BOOK,
  REQUEST_UPDATE_BOOK,
  SUCCESS_UPDATE_BOOK,
} from '../constants/actionTypes';

const initialState = {
  book: {},
  isFetchingBook: false,
  isFailedFetchBook: false,
  isUpdatingBook: false,
  isFailedUpdateBook: false,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ERROR_RECEIVE_BOOK: {
      return {
        ...state,
        isFailedFetchBook: true,
        isFetchingBook: false,
      };
    }

    case RECEIVE_BOOK: {
      return {
        ...state,
        book: action.book,
        isFailedFetchBook: false,
        isFetchingBook: false,
      }
    }

    case REQUEST_BOOK: {
      return {
        ...state,
        isFailedFetchBook: false,
        isFetchingBook: true,
      };
    }

    case ERROR_UPDATE_BOOK: {
      return {
        ...state,
        isUpdatingBook: false,
        isFailedUpdateBook: true,
      };
    }

    case REQUEST_UPDATE_BOOK: {
      return {
        ...state,
        isUpdatingBook: true,
        isFailedUpdateBook: false,
      };
    }

    case SUCCESS_UPDATE_BOOK: {
      return {
        ...state,
        isUpdatingBook: false,
        isFailedUpdateBook: false,
      }
    }

    default: return state;
  }
}