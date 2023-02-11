import { makeStyles } from '@material-ui/core/styles';
import { useIntl } from 'react-intl';
import { useDispatch, useSelector } from 'react-redux';
import Button from 'components/Button';
import PropTypes from 'prop-types';
import React, { useState, useEffect } from 'react';
import Tab from 'components/Tab';
import TabContent from 'components/TabContent';
import Tabs from 'components/Tabs';
import TabsContent from 'components/TabsContent';
import TextField from 'components/TextField';
import Typography from 'components/Typography';

import { fetchSignIn, fetchSignUpAndSignIn } from 'app/actions/user';

const getClasses = makeStyles(() => ({
  actionItem: {
    padding: '4px 0px',
  },
  actionsContainer: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    margin: '-4px 0px',
    width: '100%',
  },
  container: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
  },
  fullWidth: {
    width: '100%',
  },
  justifyCenter: {
    justifyContent: 'center',
  },
  inputField: {
    width: '100%',
  },
  loadingContainer: {
    alignItems: 'center',
    display: 'flex',
    height: '128px',
    justifyContent: 'center'
  },
  tabContent: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '24px',
  },
  paddingLeft: {
    paddingLeft: '8px',
  },
  paddingLeft3x: {
    paddingLeft: '24px',
  },
  paddingTop2x: {
    paddingTop: '16px',
  },
  paddingTop3x: {
    paddingTop: '24px',
  },
  paddingTop4x: {
    paddingTop: '32px',
  },
}));

const AVAILABLE_TABS = {
  login: 'login',
  register: 'register',
};

const initialState = {
  login: '',
  password: '',
  selectedTab: AVAILABLE_TABS.login,
};

const AuthorizationForm = ({
  onSuccess,
}) => {
  const classes = getClasses();
  const dispatch = useDispatch();
  const user = useSelector(({ user }) => user);
  const { formatMessage } = useIntl(); // дозволяє використати значення для необхідного ключа при інтернаціоналізації
  const [state, setState] = useState(initialState);

  useEffect(() => {
    if (user.isAuthorized && onSuccess) {
      onSuccess();
    }
  }, [user.isAuthorized]);

  return (
    <div className={classes.container}>
      {user.isFetchingUser && (
        <div className={classes.loadingContainer}>
          <Typography>
            Loading...
          </Typography>
        </div>
      )}
      {!user.isFetchingUser && (
        <>
          <Tabs
            fullWidth
            onChange={(event, newValue) => {
              setState({
                ...initialState,
                selectedTab: newValue,
              });
            }}
            value={state.selectedTab}
          >
            <Tab
              label={formatMessage({
                id: 'signIn',
              })}
              value={AVAILABLE_TABS.login}
            />
            <Tab
              label={formatMessage({
                id: 'signUp',
              })}
              value={AVAILABLE_TABS.register}
            />
          </Tabs>
          <TabsContent value={state.selectedTab}>
            <TabContent
              key={AVAILABLE_TABS.login}
              value={AVAILABLE_TABS.login}
            >
              <div className={classes.tabContent}>
                <div className={classes.inputField}>
                  <TextField
                    fullWidth
                    key="login"
                    label={formatMessage({
                      id: 'login',
                    })}
                    onChange={({ target }) => setState(prevState => ({
                      ...prevState,
                      login: target.value,
                    }))}
                    value={state.login}
                  />
                </div>
                <div className={classes.fullWidth}>
                  <div className={classes.paddingTop3x}>
                    <div className={classes.inputField}>
                      <TextField
                        fullWidth
                        inputType="password"
                        key="password"
                        label={formatMessage({
                          id: 'password',
                        })}
                        onChange={({ target }) => setState(prevState => ({
                          ...prevState,
                          password: target.value,
                        }))}
                        value={state.password}
                      />
                    </div>
                  </div>
                </div>
                <div className={classes.fullWidth}>
                  <div className={classes.paddingTop4x}>
                    <div className={classes.actionsContainer}>
                      <div className={classes.actionItem}>
                        <Button
                          disabled={user.isFetchingUser}
                          fullWidth
                          onClick={() => dispatch(fetchSignIn({
                            login: state.login,
                            password: state.password,
                          }))}
                          variant="outlined"
                        >
                          <Typography variant="button">
                            {formatMessage({ id: 'signIn' })}
                          </Typography>
                        </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </TabContent>
            <TabContent
              key={AVAILABLE_TABS.register}
              value={AVAILABLE_TABS.register}
            >
              <div className={classes.tabContent}>
                <div className={classes.fullWidth}>
                  <div className={classes.inputField}>
                    <TextField
                      fullWidth
                      key="login"
                      label={formatMessage({
                        id: 'login',
                      })}
                      onChange={({ target }) => setState(prevState => ({
                        ...prevState,
                        login: target.value,
                      }))}
                      value={state.login}
                    />
                  </div>
                </div>
                <div className={classes.fullWidth}>
                  <div className={classes.paddingTop3x}>
                    <div className={classes.inputField}>
                      <TextField
                        fullWidth
                        inputType="password"
                        key="password"
                        label={formatMessage({
                          id: 'password',
                        })}
                        onChange={({ target }) => setState(prevState => ({
                          ...prevState,
                          password: target.value,
                        }))}
                        value={state.password}
                      />
                    </div>
                  </div>
                </div>
                <div className={classes.fullWidth}>
                  <div className={classes.paddingTop4x}>
                    <div className={classes.actionsContainer}>
                      <div className={classes.actionItem}>
                        <Button
                          disabled={user.isFetchingSignUp}
                          fullWidth
                          onClick={() => dispatch(fetchSignUpAndSignIn({
                            login: state.login,
                            password: state.password,
                          }))}
                          variant="outlined"
                        >
                          <Typography variant="button">
                            {formatMessage({ id: 'signUp' })}
                          </Typography>
                        </Button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </TabContent>
          </TabsContent>
        </>
      )}
    </div>
  )
};

AuthorizationForm.propTypes = {
  onSuccess: PropTypes.func,
};

export default AuthorizationForm;