import { useMemo } from 'react';

export default ({
  ownedAuthorities,
  neededAuthorities,
}) => {
  return useMemo(() => {
    return neededAuthorities.every(auth => ownedAuthorities.includes(auth));
  }, [ownedAuthorities]);
}