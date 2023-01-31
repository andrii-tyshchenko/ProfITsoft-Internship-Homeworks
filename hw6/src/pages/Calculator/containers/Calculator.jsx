import React from 'react';

import GetMathTasksButton from "../components/GetMathTasksButton";
import NumberButton from "../components/NumberButton";
import OperationButton from "../components/OperationButton";
import calculatorActions from '../actions/calculator';

import TextField from "@material-ui/core/TextField";
import { withStyles } from '@material-ui/core/styles';

import { connect } from 'react-redux';


const styles = () => ({
    textFieldInputTextColor: {
        color: 'grey',
    },
});

class Calculator extends React.Component {
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

    getMathTasksFromBE = async (mathTasksCount) => {
        await calculatorActions.fetchMathTasks({
            mathTasksCount: mathTasksCount,
        })(this.props.dispatch);

        this.setState({history: this.state.history + "\nMath tasks from BE:\n"})
        const exp = /(\d+)([/*\-+])(\d+)/;
        let regExpResult;

        for (let str of this.props.list) {
            regExpResult = exp.exec(str);

            let a = +regExpResult[1];
            let sign = regExpResult[2];
            let b = +regExpResult[3];
            let result;

            switch(sign) {
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
                        result = "Division by zero!";
                        break;
                    } else {
                        result = a / b;
                        break;
                    }
                default:
                    result = "Something went wrong";
            }

            this.setState({history: this.state.history + str + "=" + result + "\n"})
        }
        this.setState({operandA: ""})
        this.setState({operandB: ""})
        this.setState({operation: ""})
    }

    render() {
        console.log(this.props);
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
                        maxRows={1000}
                    />
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
                    <GetMathTasksButton numberOfTasks={5}
                                        onClick={this.getMathTasksFromBE}/>
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

const mapReduxStateToProps = reduxState => ({
    ...reduxState,
});

const mapDispatchToProps = dispatch => ({
    dispatch,
});

export default connect(mapReduxStateToProps, mapDispatchToProps)(withStyles(styles)(Calculator));