import { Reducer, combineReducers } from "redux";
import  layoutsReducer from "./layouts"

const reducers  = combineReducers({
    layouts: layoutsReducer
})

export default reducers;