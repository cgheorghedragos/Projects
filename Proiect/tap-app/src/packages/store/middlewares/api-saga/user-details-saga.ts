import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {
    FETCH_USER_DETAILS,
    FETCH_USER_DETAILS_FAILURE,
    FETCH_USER_DETAILS_SUCCESS,
    FetchUserDetailsAction,
    FetchUserDetailsFailureAction,
    FetchUserDetailsSuccessAction
} from "../../action/user-details";
import {RESTClientService} from "../../../httpclient";
import {FETCH_USER_LOGIN_SUCCESS, FetchUserLoginSuccessAction} from "../../action/user-login";


export function* getUserDetailsSagaCall(action: FetchUserDetailsAction): SagaIterator {
   try {
        const response = yield call(RESTClientService.getUserDetailsReq, action.payload);

        yield put<FetchUserDetailsSuccessAction>({
            type: FETCH_USER_DETAILS_SUCCESS,
            payload: response
        });
        
   } catch (errors) {
     yield put<FetchUserLoginSuccessAction>({
        type: FETCH_USER_LOGIN_SUCCESS,
        payload: {access_token: "", refresh_token: ""}
     });
     window.location.href="/login"
   }
}

export default function* getUserDetailsSaga(): SagaIterator {
    yield takeLatest(FETCH_USER_DETAILS, getUserDetailsSagaCall)
}