const initialState = {
  availableItems: [
    'как+выучить+js',
    'somePath',
    'Картинка',
    'anotherPath',
  ],
};

export default (state = initialState, {type, payload}) => {
  switch (type) {

    default: return state;
  }
}