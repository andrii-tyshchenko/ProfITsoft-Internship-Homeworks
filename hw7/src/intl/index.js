import * as LANGUAGES from 'constants/languages';
const DEFAULT_LANG = LANGUAGES.en;

export default (lang) => {
  const defaultMessages = require(`./messages.json`); // прочитати файл з дефолтними значеннями

  let messages;
  try {
    messages = lang === DEFAULT_LANG
      ? defaultMessages
      : require(`./messages.${lang.toLowerCase()}.json`); // якщо це не дефолтна мова - прочитати файл для відповідної мови
  } catch (e) {
    messages = defaultMessages;
  }

  return Object
    .entries(defaultMessages)
    .reduce((result, [defaultMessageKey, defaultMessageText]) => ({
      ...result,
      [defaultMessageKey]: messages[defaultMessageKey] || defaultMessageText, // використати дефолтне значення ключа, якщо його немає для даної мови
    }), {});
};