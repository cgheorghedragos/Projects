import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {RESTClientService} from "../../../httpclient";
import axios, {AxiosError} from "axios";
import {ErrorType} from "../../../types/layouts/UserDetailsLayout";
import {
    FETCH_USER_REGISTER,
    FETCH_USER_REGISTER_FAILURE,
    FETCH_USER_REGISTER_SUCCESS,
    FetchUserRegisterAction,
    FetchUserRegisterFailureAction,
    FetchUserRegisterSuccessAction
} from "../../action/user-register";


export function* registerUserSagaCall(action: FetchUserRegisterAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.registerUser, action.payload);

        yield put<FetchUserRegisterSuccessAction>({
            type: FETCH_USER_REGISTER_SUCCESS,
            payload: response
        });

    } catch (error) {
        let errorMessage = 'An unknown error occurred';
        if (axios.isAxiosError(error)){
            const axiosError = error as AxiosError<ErrorType>;
            errorMessage = axiosError.response?.data.error || ''
        }

        yield put<FetchUserRegisterFailureAction>({
            type: FETCH_USER_REGISTER_FAILURE,
            payload: errorMessage
        });
    }
}

export default function* userRegisterSaga(): SagaIterator {
    yield takeLatest(FETCH_USER_REGISTER, registerUserSagaCall)
}