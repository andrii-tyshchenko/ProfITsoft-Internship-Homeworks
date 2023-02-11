import { getJson, postJson } from 'requests';
import { clearToken, getToken, setToken } from 'token';
import config from 'config';

import {
  ERROR_RECEIVE_USER,
  ERROR_SIGN_IN,
  ERROR_SIGN_OUT,
  ERROR_SIGN_UP,
  RECEIVE_USER,
  REQUEST_SIGN_IN,
  REQUEST_SIGN_OUT,
  REQUEST_SIGN_UP,
  REQUEST_USER,
  SUCCESS_SIGN_IN,
  SUCCESS_SIGN_OUT,
  SUCCESS_SIGN_UP,
} from '../constants/actionTypes';

const errorReceiveUser = () => ({
  type: ERROR_RECEIVE_USER,
});

const getUser = () => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return getJson({ // самописна ф-я, симулюємо звернення до БЕ щодо юзера
    url: `${BASE_URL}${USERS_SERVICE}/user/get`,
  }).catch(() => {
    const storage = { // фейкова відповідь від БЕ
      'token_for_admin': {
        authorities: [
          'ПРАВА_АДМІНА',
          'ПРАВА_ЮЗЕРА',
        ],
        name: 'Admin',
      },
      'token_for_user': {
        authorities: [
          'ПРАВА_ЮЗЕРА',
        ],
        name: 'Regular user',
      }
    };

    const token = getToken();

    return storage[token]; // user у цьому прикладі - це name + authorities[] ?
  });
};

const requestUser = () => ({
  type: REQUEST_USER,
});

const receiveUser = (user) => ({
  type: RECEIVE_USER,
  payload: user,
});

export const fetchUser = () => (dispatch) => {
  if (getToken()) {
    dispatch(requestUser());

    return getUser({
      dispatch,
    }).then(user => dispatch(receiveUser(user)))
      .catch(() => dispatch(errorReceiveUser()));
  }
};

const errorSignIn = () => ({
  type: ERROR_SIGN_IN,
});

const requestSignIn = () => ({
  type: REQUEST_SIGN_IN,
});

const successSignIn = (payload) => ({
  payload,
  type: SUCCESS_SIGN_IN,
});

const signIn = ({
  login,
  password,
}) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return postJson({ // самописна ф-я, симулюємо авторизацію ?
    body: {
      login,
      password,
    },
    url: `${BASE_URL}${USERS_SERVICE}/user/signIn`,
  }).catch(() => {
    const storage = {
      'admin_123': {
        authorities: [
          'ПРАВА_АДМІНА',
          'ПРАВА_ЮЗЕРА',
        ],
        name: 'Admin',
        token: 'token_for_admin',
      },
      'user_456': {
        authorities: [
          'ПРАВА_ЮЗЕРА',
        ],
        name: 'Regular user',
        token: 'token_for_user',
      }
    };
    return storage[`${login}_${password}`];
  });
};

export const fetchSignIn = ({
  login,
  password,
}) => (dispatch) => {
  dispatch(requestSignIn());

  return signIn({
    login,
    password,
  }).then((response) => {
    setToken(response.token);

    dispatch(successSignIn(response));
  }).catch(() => dispatch(errorSignIn()));
};

// логіку з реєстрації пропустити
const errorSignUp = errors => ({
  payload: errors,
  type: ERROR_SIGN_UP,
});

const requestSignUp = () => ({
  type: REQUEST_SIGN_UP,
});

const successSignUp = () => ({
  type: SUCCESS_SIGN_UP,
});

const signUp = ({
  login,
  password,
}) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return postJson({
    body: {
      login,
      password,
    },
    url: `${BASE_URL}${USERS_SERVICE}/user/signUp`,
  });
};

export const fetchSignUp = ({
  login,
  password,
}) => (dispatch) => {
  dispatch(requestSignUp());

  return signUp({
    login,
    password,
  }).then(() => dispatch(successSignUp()))
    .catch(() => dispatch(errorSignUp()));
};

export const fetchSignUpAndSignIn = ({
  login,
  password,
}) => (dispatch) => {
  return dispatch(fetchSignUp({
    login,
    password,
  })).then(() => {
    return dispatch(fetchSignIn({ // перенаправити на сторінку входу у разі успішної реєстрації?
      login,
      password,
    }))
  });
};

const errorSignOut = (errors) => ({
  payload: errors,
  type: ERROR_SIGN_OUT,
});

const requestSignOut = () => ({
  type: REQUEST_SIGN_OUT,
});

const successSignOut = () => ({
  type: SUCCESS_SIGN_OUT,
});

const signOut = () => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return postJson({
    url: `${BASE_URL}${USERS_SERVICE}/user/signOut`,
  }).catch(() => {
    // this catch() is just a stub
  });
};

export const fetchSignOut = () => (dispatch) => {
  dispatch(requestSignOut());

  return signOut()
    .then(() => {
      clearToken();

      dispatch(successSignOut());
  }).catch(() => dispatch(errorSignOut()))
};