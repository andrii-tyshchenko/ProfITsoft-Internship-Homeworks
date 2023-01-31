const initialState = {
    isLoading: false,
    isError: false,
    list: [],
};

export default (state = initialState, action) => {
    switch (action.type) {
        case 'ERROR_RECEIVE_MATH_TASKS': {
            return {
                ...state,
                isLoading: false,
                isError: true,
            }
        }
        case 'REQUEST_MATH_TASKS': {
            return {
                ...state,
                isLoading: true,
            };
        }
        case 'RECEIVE_MATH_TASKS': {
            const {
                mathTasks,
            } = action;
            return {
                ...state,
                isLoading: false,
                list: mathTasks,
            };
        }
        default:
            return state;
    }
};