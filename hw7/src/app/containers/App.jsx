// базовий компонент (контейнер)
import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { BrowserRouter, Switch, Redirect, Route } from 'react-router-dom';
import IntlProvider from 'components/IntlProvider';
import Header from 'components/Header';
import Books from "pageProviders/Books";
import NewBook from "pageProviders/NewBook";
import EditBook from "pageProviders/EditBook";
import PageInitial from 'pageProviders/Initial';
import PageLogin from 'pageProviders/Login';
import * as PAGES from 'constants/pages';
import { fetchUser } from '../actions/user';

const App = () => {
  const [state, setState] = useState({
    componentDidMount: false,
  });
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchUser());
    setState(prevState => ({
      ...prevState,
      componentDidMount: true,
    }));
  }, []);

  return (
    <BrowserRouter>
      <IntlProvider> {/*компонент-обгортка, що дозволяє динамічно змінювати мову, без перезавантаження сторінки*/}
        <Header />
        {state.componentDidMount && (
            <Switch>
              <Route exact
                     path={`/${PAGES.LOGIN}`}>
                <PageLogin />
              </Route>
              <Route exact
                     path={`/${PAGES.INITIAL}`}>
                <PageInitial />
              </Route>
              <Route exact // завдяки exact працюватиме лише /books, а от /books/some_other_page буде редіректити на initial
                     path={`/${PAGES.BOOKS}`}>
                <Books />
              </Route>
              <Route exact
                     path={`/${PAGES.BOOKS}/new`}>
                <NewBook />
              </Route>
              <Route path={`/${PAGES.BOOKS}/:id`}>
                <EditBook />
              </Route>
              <Redirect from="*" to={`/${PAGES.INITIAL}`} />
            </Switch>
        )}
      </IntlProvider>
    </BrowserRouter>
  );
};

export default App;