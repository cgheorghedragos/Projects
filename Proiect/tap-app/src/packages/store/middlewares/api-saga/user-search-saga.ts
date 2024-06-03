import { SagaIterator } from "redux-saga";
import { takeLatest, put, call } from "redux-saga/effects";
import {
    FETCH_SEARCH_USER_DETAILS,
    FETCH_SEARCH_USER_DETAILS_FAILURE,
    FETCH_SEARCH_USER_DETAILS_SUCCESS,
    FetchSearchUserDetailsAction,
    FetchSearchUserDetailsFailureAction,
    FetchSearchUserDetailsSuccessAction
} from "../../action/user-search";
import {RESTClientService} from "../../../httpclient";


export function* getSearchUserDetailsSagaCall(action: FetchSearchUserDetailsAction): SagaIterator {
    try {

        const response = yield call(RESTClientService.getSearchUserDetailsReq, action.payload);

        yield put<FetchSearchUserDetailsSuccessAction>({
            type: FETCH_SEARCH_USER_DETAILS_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchSearchUserDetailsFailureAction>({
            type: FETCH_SEARCH_USER_DETAILS_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* getSearchUserDetailsSaga(): SagaIterator {
    yield takeLatest(FETCH_SEARCH_USER_DETAILS, getSearchUserDetailsSagaCall)
}