import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {RESTClientService} from "../../../httpclient";
import {
    FETCH_USER_LOGIN,
    FETCH_USER_LOGIN_FAILURE,
    FETCH_USER_LOGIN_SUCCESS,
    FetchUserLoginAction,
    FetchUserLoginFailureAction,
    FetchUserLoginSuccessAction
} from "../../action/user-login";
import axios, {AxiosError} from "axios";
import {ErrorResponse} from "react-router-dom";
import {ErrorType} from "../../../types/layouts/UserDetailsLayout";


export function* loginUserSagaCall(action: FetchUserLoginAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.loginUser, action.payload);

        yield put<FetchUserLoginSuccessAction>({
            type: FETCH_USER_LOGIN_SUCCESS,
            payload: response
        });

    } catch (error) {
        let errorMessage = 'An unknown error occurred';
        if (axios.isAxiosError(error)){
            const axiosError = error as AxiosError<ErrorType>;
            errorMessage = axiosError.response?.data.error || ''
        }

        yield put<FetchUserLoginFailureAction>({
            type: FETCH_USER_LOGIN_FAILURE,
            payload: errorMessage
        });
    }
}

export default function* userLoginSaga(): SagaIterator {
    yield takeLatest(FETCH_USER_LOGIN, loginUserSagaCall)
}