import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useDispatch, useSelector } from 'react-redux';
import Link from 'components/Link';
import useAccessValidate from 'hooks/useAccessValidate';
import { useIntl } from "react-intl";

import Button from 'components/Button';
import TableContainer from 'components/TableContainer';
import Table from 'components/Table';
import TableHead from 'components/TableHead';
import TableRow from 'components/TableRow';
import TableCell from 'components/TableCell';
import TableBody from 'components/TableBody';
import Typography from 'components/Typography';

import { fetchBooks, fetchDeleteBook } from "../actions/books";

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
}));

const Books = ({
  authorities,
}) => {
  const classes = getClasses();

  const {
    books,
    isFetchingBooks,
    isFailedFetchBooks,
  } = useSelector(({ reducer }) => reducer);

  const canCreateUpdateDeleteBooks = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['ПРАВА_АДМІНА'],
  });

  const { formatMessage } = useIntl(); // дозволяє використати значення для необхідного ключа при інтернаціоналізації

  const [state, setState] = useState({
    componentDidMount: false,
    shouldDeleteBook: false,
    bookIdThatShouldBeDeleted: 0,
  });

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchBooks());

    setState(prevState => ({
      ...prevState,
      componentDidMount: true,
    }));
  }, [state.shouldDeleteBook]);

  useEffect(() => {
    if (state.shouldDeleteBook) {
      dispatch(fetchDeleteBook(state.bookIdThatShouldBeDeleted));

      setState(prevState => ({
        ...prevState,
        shouldDeleteBook: false,
        bookIdThatShouldBeDeleted: 0,
      }));
    }
  }, [state.shouldDeleteBook]);

  return (
    <div className={classes.container}>
      {canCreateUpdateDeleteBooks && // відобразиться, лише якщо є необхідні authorities
          <div>
            <Button variant="outlined">
              <Link to={location => ({
                ...location,
                pathname: '/books/new',
              })}
              >
                <Typography variant="button">
                  {formatMessage({id: 'create'})}
                </Typography>
              </Link>
            </Button>
          </div>
      }

      {isFetchingBooks && // відобразиться, якщо йде процес завантаження
          <div>
            Loading...
          </div>
      }

      <TableContainer>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>id</TableCell>
              <TableCell>Title</TableCell>
              <TableCell>Author</TableCell>
              <TableCell>Publisher</TableCell>
              <TableCell>ISBN</TableCell>
              <TableCell>Publishing year</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {books.map(book => (
                <TableRow
                    key={book.id}
                    hover={true}
                >
                  <TableCell>{book.id}</TableCell>
                  <TableCell>{book.title}</TableCell>
                  <TableCell>{book.author}</TableCell>
                  <TableCell>{book.publisherName}</TableCell>
                  <TableCell>{book.isbn}</TableCell>
                  <TableCell>{book.publishingYear}</TableCell>

                  {/* кнопки "Видалити" і "Редагувати" */}
                  {/* як зробити, щоб вони з'являлись і зникали при наведенні на рядок?? */}
                  {/* бачать лише адміни */}
                  {canCreateUpdateDeleteBooks &&
                    <TableCell>
                      <Button variant="outlined"
                              id={book.id}
                              onClick={ () => setState(prevState => ({
                                ...prevState,
                                shouldDeleteBook: true,
                                bookIdThatShouldBeDeleted: book.id,
                              })) }
                      >
                        <Typography variant="button">
                          {formatMessage({ id: 'delete' })}
                        </Typography>
                      </Button>
                    </TableCell>
                  }
                  {canCreateUpdateDeleteBooks &&
                      <TableCell>
                        <Button variant="outlined">
                          <Link to={location => ({
                            ...location,
                            pathname: `/books/${book.id}`,
                          })}
                          >
                            <Typography variant="button">
                              {formatMessage({id: 'edit'})}
                            </Typography>
                          </Link>
                        </Button>
                      </TableCell>
                  }
                </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      {isFailedFetchBooks && <div>Error fetching books</div>} {/* як вивести помилку, отриману з БЕ? */}
    </div>
  )
};

export default Books;