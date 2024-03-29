const receiveMathTasks = (mathTasks) => ({ // отримуємо приклади, якщо запит до BE успішно виконано
    mathTasks,
    type: 'RECEIVE_MATH_TASKS'
});

const requestMathTasks = () => ({ // запитуємо приклади з BE
    type: 'REQUEST_MATH_TASKS'
});

const errorReceiveMathTasks= () => ({ // якщо під час запиту до BE сталася помилка
    type: 'ERROR_RECEIVE_MATH_TASKS'
});

const getMathTasks = (mathTasksCount) => { // робимо запит до BE
    const url = 'http://localhost:8080/math/mathtask?count=' + mathTasksCount; // ! обов'язково з http://

    return fetch(url)
        .then(response => response.json())
        .catch(error => alert(error))
};

const fetchMathTasks = ({ mathTasksCount }) => (dispatch) => {
    dispatch(requestMathTasks()); // Повідомляю стору, що роблю запит прикладів

    return getMathTasks(mathTasksCount) // Викликаю функцію запиту прикладів
        .then(mathTasks => dispatch(receiveMathTasks(mathTasks))) // якщо успіх
        .catch(() => dispatch(errorReceiveMathTasks())); // якщо помилка
};

export default {
    fetchMathTasks,
};