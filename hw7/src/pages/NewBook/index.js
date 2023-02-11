import React from 'react';
import { applyMiddleware, createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import thunkMiddleware from "redux-thunk";
import withAuthorities from "decorators/withAuthorities";
import NewBook from './containers/NewBook';
import reducer from "./reducers/newBook";

const rootReducer = combineReducers({
    reducer,
});

const store = createStore(
    rootReducer,
    applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
    <Provider store={store}>
        <NewBook {...props} />
    </Provider>
));