import React from 'react';
import { applyMiddleware, createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import thunkMiddleware from "redux-thunk";
import withAuthorities from "decorators/withAuthorities";
import EditBook from './containers/EditBook';
import reducer from "./reducers/editBook";

const rootReducer = combineReducers({
    reducer,
});

const store = createStore(
    rootReducer,
    applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
    <Provider store={store}>
        <EditBook {...props} />
    </Provider>
));