import { getToken } from 'token';

const getHeaders = () => ({
  Accept: 'application/json',
  Authorization: `Bearer ${getToken()}`,
  'Content-Type': 'application/json',
});

const fetchGet = ({ params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
    url,
    {
      headers: getHeaders(),
      method: 'GET',
    }
  );
};

const fetchPost = ({ body, params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
    url,
    {
      body: JSON.stringify(body),
      headers: getHeaders(),
      method: 'POST',
    }
  );
};

const fetchPut = ({ body, params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
      url,
      {
        body: JSON.stringify(body),
        headers: getHeaders(),
        method: 'PUT',
      }
  );
};

const fetchDelete = ({ params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
      url,
      {
        headers: getHeaders(),
        method: 'DELETE',
      }
  );
};

export const getJson = ({
  params,
  url,
}) => {
  return fetchGet({
    params,
    url,
  }).then((response) => {
    if (response.ok) {
      return response.json();
    }

    throw response;
  });
};

export const postJson = ({
  body,
  params,
  url,
}) => {
  return fetchPost({
    body,
    params,
    url,
  }).then((response) => {
    if (response.ok) {
      return response.json();
    }

    throw response;
  });
};

export const putJson = ({
  body,
  params,
  url,
}) => {
  return fetchPut({
    body,
    params,
    url,
  }).then((response) => {
    if (response.ok) {
      return response;
    } else {
      throw response;
    }
  });
};

export const deleteJson = ({
  params,
  url,
}) => {
  return fetchDelete({
    params,
    url,
  }).then((response) => {
    if (response.ok) {
      return response.json();
    }

    throw response;
  });
};