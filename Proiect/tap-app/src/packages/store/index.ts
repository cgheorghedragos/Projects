import createSagaMiddleware from "redux-saga"
import {configureStore, Store} from "@reduxjs/toolkit";
import reducers from "../state";
import rootSaga from "./middlewares/root-saga";

const saga =  createSagaMiddleware();

export default function applicationStore() {

    const store = configureStore(
        {
            reducer: reducers,
            middleware: getDefaultMiddleware => getDefaultMiddleware().concat(saga)
        }
    );

    saga.run(rootSaga);

    return store;
}