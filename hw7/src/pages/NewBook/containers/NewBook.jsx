import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useIntl } from "react-intl";
import Button from "components/Button";
import Link from 'components/Link';
import TextField from 'components/TextField';
import Typography from 'components/Typography';
import useAccessValidate from 'hooks/useAccessValidate';
import { useDispatch, useSelector } from "react-redux";
import { fetchSaveBook } from "../actions/newBook";
import { useHistory } from "react-router-dom";

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
  warningText: {
    color: 'red'
  },
  textField: {
    width: 500,
  }
}));

const NewBook = ({ authorities }) => {
  const classes = getClasses();

  const canSeeForm = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['ПРАВА_АДМІНА'],
  });

  const {
    book,
    isFailedSaveBook,
  } = useSelector(({ reducer }) => reducer);

  const { formatMessage } = useIntl();

  const dispatch = useDispatch();

  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault()

    dispatch(fetchSaveBook(book));

    // чогось бачить попереднє значення
    if (!isFailedSaveBook) {
      history.push("/books"); // redirect на сторінку зі списком книг, якщо успішно зберегли
    }
  }

  return (
      <div className={classes.container}>
        {isFailedSaveBook && <div className={classes.warningText}>Error saving book</div>}
        {/* як вивести помилку, отриману з БЕ? */}

        {canSeeForm &&
            <form onSubmit={handleSubmit}>
              <TextField label="Title"
                         className={classes.textField}
                         onChange={event => book.title = event.target.value} />
              <br/>
              <TextField label="Author"
                         className={classes.textField}
                         onChange={event => book.author = event.target.value} />
              <br/>
              <TextField label="Publisher id" // наразі в БД є лише 2 Видавці - з id 1 і 2
                         type="number"
                         className={classes.textField}
                         onChange={event => book.publisherId = event.target.value} />
              <br/>
              <TextField label="ISBN"
                         className={classes.textField}
                         onChange={event => book.isbn = event.target.value}/>
              <br/>
              <TextField label="Publishing year"
                         type="number"
                         className={classes.textField}
                         onChange={event => book.publishingYear = event.target.value} />
              <br/>
              <br/>
              <div>
                <Button variant="outlined">
                  <Link to={location => ({
                    ...location,
                    pathname: '/books',
                  })}
                  >
                    <Typography variant="button">
                      {formatMessage({id: 'cancel'})}
                    </Typography>
                  </Link>
                </Button>
                <Button variant="outlined"
                        type="submit" >
                  <Typography variant="button">
                    {formatMessage({id: 'save'})}
                  </Typography>
                </Button>
              </div>
            </form>
        }

        {!canSeeForm &&
            <div className={classes.warningText}>
              WARNING! You don't have a permission to add new books!
            </div>
        }
      </div>
  )
};

export default NewBook;