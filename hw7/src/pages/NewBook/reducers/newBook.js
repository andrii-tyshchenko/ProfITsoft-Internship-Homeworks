import {
  ERROR_SAVE_BOOK,
  REQUEST_SAVE_BOOK,
  SUCCESS_SAVE_BOOK,
} from '../constants/actionTypes';

const initialState = {
  book: {},
  isSavingBook: false,
  isFailedSaveBook: false,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ERROR_SAVE_BOOK: {
      return {
        ...state,
        book: {},
        isSavingBook: false,
        isFailedSaveBook: true,
      };
    }

    case REQUEST_SAVE_BOOK: {
      return {
        ...state,
        isSavingBook: true,
        isFailedSaveBook: false,
      };
    }

    case SUCCESS_SAVE_BOOK: {
      return {
        ...state,
        book: {},
        isSavingBook: false,
        isFailedSaveBook: false,
      }
    }

    default: return state;
  }
}