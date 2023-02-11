import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useSelector } from 'react-redux';
import Link from 'components/Link';
import Typography from 'components/Typography';
import useAccessValidate from 'hooks/useAccessValidate';

const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
}));

const Initial = ({
  authorities,
}) => {
  const classes = getClasses();

  const {
    availableItems,
  } = useSelector(({ reducer })=> reducer);

  const canSeeList = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['ПРАВА_АДМІНА'],
  });

  return (
    <div className={classes.container}>
      {canSeeList && availableItems.map((item, index) => ( // відобразиться, лише якщо є необхідні authorities
        <Link
          href={index % 2 === 0
            ? `https://www.google.com.ua/search?q=${item}&hl=ru`
            : undefined}
          to={index % 2 !== 0
            ? (location => ({
              ...location,
              pathname: `/${item}`,
              search: `${location.search}&newProp=42`,
            }))
            : undefined}
        >
          <Typography>
            {item}
          </Typography>
        </Link>
      ))}

      {/*{!canSeeList && (*/}
      {/*  <Typography>*/}
      {/*    Не можу нічого показати :(*/}
      {/*  </Typography>*/}
      {/*)}*/}

      <Link to={location => ({ // відобразиться для будь-яких authorities
                  ...location,
                  pathname: '/books'})}
      >
        <Typography>
          List of books
        </Typography>
      </Link>
    </div>
  )
};

export default Initial;