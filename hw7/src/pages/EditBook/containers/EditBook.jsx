import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useIntl } from "react-intl";
import { useHistory, useParams } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux';
import { fetchBook, fetchUpdateBook } from "../actions/editBook";
import Button from "components/Button";
import Link from 'components/Link';
import TextField from 'components/TextField';
import Typography from 'components/Typography';
import useAccessValidate from 'hooks/useAccessValidate';

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

const EditBook = ({ authorities }) => {
  const classes = getClasses();

  const canSeeForm = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['ПРАВА_АДМІНА'],
  });

  const {
    book,
    isFetchingBook,
    isFailedFetchBook,
    isFailedUpdateBook,
  } = useSelector(({ reducer }) => reducer);

  const { formatMessage } = useIntl();

  let { id } = useParams(); // дає можливість отримати id з URL-адреси

  const [state, setState] = useState({
    componentDidMount: false,
  });

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchBook(id));

    setState(prevState => ({
      ...prevState,
      componentDidMount: true,
    }));
  }, [isFailedUpdateBook]);

  const history = useHistory();

  const handleSubmit = (event) => {
    event.preventDefault()

    dispatch(fetchUpdateBook(id, book));

    // чогось бачить попереднє значення, хоча на сторінці рендериться актуальне
    if (!isFailedUpdateBook) {
      history.push("/books"); // redirect на сторінку зі списком книг, якщо успішно оновили
    }
  }

  return (
      <div className={classes.container}>
        {isFailedUpdateBook && <div className={classes.warningText}>Error updating book</div>}
        {/* як вивести помилку, отриману з БЕ? */}

        {!isFailedFetchBook && !isFetchingBook && canSeeForm &&
            <form onSubmit={handleSubmit}>
              <TextField label="Title"
                         defaultValue={book.title}
                         className={classes.textField}
                         onChange={event => book.title = event.target.value} />
              <br/>
              <TextField label="Author"
                         defaultValue={book.author}
                         className={classes.textField}
                         onChange={event => book.author = event.target.value} />
              <br/>
              <TextField label="Publisher id" // наразі в БД є лише 2 Видавці - з id 1 і 2
                         type="number"
                         defaultValue={book.publisherId}
                         className={classes.textField}
                         onChange={event => book.publisherId = event.target.value} />
              <br/>
              <TextField label="ISBN"
                         defaultValue={book.isbn}
                         className={classes.textField}
                         onChange={event => book.isbn = event.target.value}/>
              <br/>
              <TextField label="Publishing year"
                         type="number"
                         defaultValue={book.publishingYear}
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
              WARNING! You don't have a permission to edit books!
            </div>
        }

        {isFailedFetchBook && <div className={classes.warningText}>Error fetching book</div>}
        {/* як вивести помилку, отриману з БЕ? */}
      </div>
  )
};

export default EditBook;