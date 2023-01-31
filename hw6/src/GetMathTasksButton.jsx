import React from "react";
import Button from '@material-ui/core/Button';

class GetMathTasksButton extends React.Component {
    render() {
        return (
            <Button variant="contained" color="secondary">
                Get math tasks from BE
            </Button>
        );
    }
}

export default GetMathTasksButton;