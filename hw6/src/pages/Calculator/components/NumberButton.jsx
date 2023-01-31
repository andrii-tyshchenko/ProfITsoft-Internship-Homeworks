import React from "react";
import Button from '@material-ui/core/Button';

class NumberButton extends React.Component {
    render() {
        return (
            <Button variant="contained" onClick={() => {this.props.onClick(this.props.number)}}>
                {this.props.number}
            </Button>
        );
    }
}

export default NumberButton;