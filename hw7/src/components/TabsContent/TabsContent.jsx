import PropTypes from 'prop-types';

const TabsContent = ({
  children,
  value,
}) => {
  return (children instanceof Array ? children : [children])
    .find(child => child.props.value === value);
};


TabsContent.propTypes = {
  children: PropTypes.array.isRequired,
  value: PropTypes.string.isRequired,
};

export default TabsContent;