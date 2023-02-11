import {
  ERROR_RECEIVE_USER,
  ERROR_SIGN_IN,
  ERROR_SIGN_OUT,
  ERROR_SIGN_UP,
  RECEIVE_USER,
  REQUEST_SIGN_OUT,
  REQUEST_SIGN_IN,
  REQUEST_SIGN_UP,
  REQUEST_USER,
  SUCCESS_SIGN_IN,
  SUCCESS_SIGN_OUT,
  SUCCESS_SIGN_UP,
} from '../constants/actionTypes';

const initialState = {
  authorities: [],
  name: '',
  isAuthorized: false,
  isFailedFetchUser: false,
  isFailedSignUp: false,
  isFailedSignOut: false,
  isFetchingUser: false,
  isFetchingSignOut: false,
  isFetchingSignUp: false,
};

export default (state = initialState, action) => {
  switch (action.type) {
    case ERROR_RECEIVE_USER:
    case ERROR_SIGN_IN: {
      return {
        ...state,
        isFailedFetchUser: true,
        isFetchingUser: false,
      };
    }

    case ERROR_SIGN_OUT: {
      return {
        ...state,
        isFailedSignOut: true,
        isFetchingSignOut: false,
      };
    }

    case ERROR_SIGN_UP: {
      return {
        ...state,
        isFailedSignUp: true,
        isFetchingSignUp: false,
      };
    }

    case RECEIVE_USER:
    case SUCCESS_SIGN_IN: {
      const {
        authorities,
        name,
      } = action.payload;

      return {
        ...state,
        authorities: authorities || initialState.authorities,
        name: name || initialState.name,
        isAuthorized: true,
        isFailedFetchUser: false,
        isFetchingUser: false,
      };
    }

    case REQUEST_SIGN_OUT: {
      return {
        ...state,
        isFailedSignOut: false,
        isFetchingSignOut: true,
      };
    }

    case REQUEST_SIGN_UP: {
      return {
        ...state,
        isFailedSignUp: false,
        isFetchingSignUp: true,
      };
    }

    case REQUEST_SIGN_IN:
    case REQUEST_USER: {
      return {
        ...state,
        isFailedFetchUser: false,
        isFetchingUser: true,
      };
    }

    case SUCCESS_SIGN_OUT: {
      return initialState;
    }

    case SUCCESS_SIGN_UP: {
      return {
        ...state,
        isFailedSignUp: false,
        isFetchingSignUp: false,
      };
    }

    default: return state;
  }
}