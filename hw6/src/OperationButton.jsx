import React from "react";
import Button from '@material-ui/core/Button';

class OperationButton extends React.Component {
    render() {
        return (
            <Button variant="contained" color="primary" onClick={() => {this.props.onClick(this.props.operation)}}>
                {this.props.operation}
            </Button>
        );
    }
}

export default OperationButton;