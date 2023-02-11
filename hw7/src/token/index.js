// файл для маніпуляцій з токеном у Local Storage браузера
const LOCAL_STORAGE_KEYS = {
  TOKEN: 'token',
};

export const getToken = () => {
  return localStorage.getItem(LOCAL_STORAGE_KEYS.TOKEN); // отримати з Local Storage об'єкт з назвою 'token'
};

export const setToken = (token) => {
  localStorage.setItem(LOCAL_STORAGE_KEYS.TOKEN, token); // встановити об'єкту з назвою 'token' значення з аргумента token
};

export const clearToken = () => {
  localStorage.removeItem(LOCAL_STORAGE_KEYS.TOKEN); // видалити з Local Storage об'єкт з назвою 'token'
};