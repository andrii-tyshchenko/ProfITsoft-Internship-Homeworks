import React from 'react';
import Calculator from './pages/Calculator/containers/Calculator';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import calculatorReducer from './pages/Calculator/reducers/calculator';

const store = createStore(calculatorReducer);

const App = () => {
    return (
        <Provider store={store}>
            <Calculator />
        </Provider>
    )
};

export default App;