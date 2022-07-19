import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';

export const store = configureStore({
  //way to change states in Redux
  reducer: {
    counter: counterReducer,
  },
});
