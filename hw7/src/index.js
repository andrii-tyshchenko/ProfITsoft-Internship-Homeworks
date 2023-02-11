// файл, в якому здійснюється вбудовування реакт-проекту в основний HTML код сайту
import React from 'react';
import ReactDOM from 'react-dom';
import App from './app/index.js';

ReactDOM.render(
  <App />,
  document.getElementById("root")
);