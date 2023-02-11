import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useDispatch, useSelector } from 'react-redux';
import { useIntl } from 'react-intl';
import Button from 'components/Button';
import MenuItem from 'components/MenuItem';
import Select from 'components/Select';
import Typography from 'components/Typography';
import useChangePage from 'hooks/useChangePage';
import useLocationSearch from 'hooks/useLocationSearch';
import { fetchSignOut } from 'app/actions/user';
import * as languages from 'constants/languages';

const getClasses = makeStyles(() => ({
  container: {
    alignItems: 'center',
    display: 'flex',
    background: '#333333',
    height: '48px',
  },
  content: {
    alignItems: 'center',
    display: 'flex',
    padding: '8px',
    width: '100%',
    justifyContent: 'flex-end',
  },
  paddingLeft: {
   paddingLeft: '8px',
  },
}));

const INTERFACE_LANGUAGES = {
  en: 'English',
  ru: 'Русский',
  ua: 'Українська',
};

const Header = () => {
  const user = useSelector(({ user }) => user);
  const { formatMessage } = useIntl(); // дозволяє використати значення для необхідного ключа при інтернаціоналізації
  const dispatch = useDispatch();
  const changePage = useChangePage();
  const classes = getClasses();
  const locationSearch = useLocationSearch();

  return (
    <div className={classes.container}>
      <div className={classes.content}>
        {user.isAuthorized && (
          <>
            <Typography>
              <div style={{ color: 'yellow' }}>
                {user.name}
              </div>
            </Typography>
            <div className={classes.paddingLeft}>
              <Button
                onClick={() => dispatch(fetchSignOut())}
              >
                <Typography>
                  <div style={{ color: 'white' }}>
                    {formatMessage({
                      id: 'logout',
                    })}
                  </div>
                </Typography>
              </Button>
            </div>
          </>
        )}
        <div className={classes.paddingLeft}>
          <Select
            label={formatMessage({
              id: 'language'
            })}
            onChange={({ target }) => {
              changePage({
                locationSearch: {
                  ...locationSearch,
                  lang: target.value,
                },
              });
            }}
            style={{
              color: 'white'
            }}
            value={locationSearch.lang}
          >
            {Object.values(languages).map(langCode => (
              <MenuItem value={langCode}>
                <Typography>
                  {INTERFACE_LANGUAGES[langCode]}
                </Typography>
              </MenuItem>
            ))}
          </Select>
        </div>
      </div>
    </div>
  )
};

export default Header;