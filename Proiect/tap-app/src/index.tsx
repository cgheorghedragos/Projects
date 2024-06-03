import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './pages/app/App';
import { Provider } from 'react-redux';
import applicationStore from "./packages/store";

const store = applicationStore();

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <Provider store={store}>
    <App/>
  </Provider>
);