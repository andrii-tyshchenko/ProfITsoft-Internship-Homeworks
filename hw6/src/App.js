import React from 'react';
import GetMathTasksButton from "./GetMathTasksButton";
import NumberButton from "./NumberButton";
import OperationButton from "./OperationButton";
import TextField from "@material-ui/core/TextField";
import { withStyles } from '@material-ui/core/styles';

const styles = () => ({
    textFieldInputTextColor: {
        color: 'grey',
    },
});

class App extends React.Component {
    constructor(props) {
        super(props);

        this.state={
            history: "",
            operandA: "",
            operandB: "",
            operation: ""
        };
    }

    updateHistory = (value) => {
        let regExp = /^\d+$/;

        if (this.state.operation === "") {
            if (regExp.test(value)) {
                this.setState({history: this.state.history + value})
                this.setState((prevState) => ({operandA: prevState.operandA + value}));
            } else {
                if (this.state.operandA !== "" && value !== "=") {
                    this.setState({operation: value})
                    this.setState({history: this.state.history + value})
                }
            }
        } else {
            if (regExp.test(value)) {
                this.setState({history: this.state.history + value})
                this.setState((prevState) => ({operandB: prevState.operandB + value}));
            } else {
                if (this.state.operandB === "") {
                    if (value === "=") {
                        return;
                    }
                    this.setState({operation: value})
                    this.setState({history: this.state.history.substring(0, this.state.history.length - 1) + value})
                } else {
                    let a = +this.state.operandA;
                    let b = +this.state.operandB;
                    let result;

                    switch(this.state.operation) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        case "/":
                            if (b === 0) {
                                // result = "Division by zero!";
                                alert("Division by zero! Insert other number")
                                return;
                            } else {
                                result = a / b;
                            }
                            break;
                        default:
                            result = "Something went wrong";
                    }
                    this.setState({operandA: "" + result})
                    this.setState({operandB: ""})

                    if (value === "=") {
                        this.setState({operation: ""})
                        this.setState({history: this.state.history + "=" + result})
                    } else {
                        this.setState({operation: "" + value})
                        this.setState({history: this.state.history + "=" + result + value})
                    }
                }
            }
        }
    }

    render() {
        return (
            <div>
                <div>
                    <TextField
                        id="filled-multiline-static"
                        // label="Calculation history"
                        multiline
                        defaultValue={this.state.history}
                        variant="filled"
                        InputProps={{
                            className: this.props.classes.textFieldInputTextColor
                        }}
                    />
                    <GetMathTasksButton />
                </div>
                <div>
                    <NumberButton number={1}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={2}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={3}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={4}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={5}
                                  onClick={this.updateHistory}/>
                </div>
                <div>
                    <NumberButton number={6}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={7}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={8}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={9}
                                  onClick={this.updateHistory}/>
                    <NumberButton number={0}
                                  onClick={this.updateHistory}/>
                </div>
                <div>
                    <OperationButton operation="/"
                                     onClick={this.updateHistory}/>
                    <OperationButton operation="*"
                                     onClick={this.updateHistory}/>
                    <OperationButton operation="+"
                                     onClick={this.updateHistory}/>
                    <OperationButton operation="-"
                                     onClick={this.updateHistory}/>
                    <OperationButton operation="="
                                     onClick={this.updateHistory}/>
                </div>
            </div>
        );
    }
}

export default withStyles(styles)(App);