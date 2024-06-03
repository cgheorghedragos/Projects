import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {RESTClientService} from "../../../../httpclient";
import {
    FETCH_UPDATE_STUDIES,
    FETCH_UPDATE_STUDIES_FAILURE, FETCH_UPDATE_STUDIES_SUCCESS,
    FetchUpdateStudiesAction, FetchUpdateStudiesFailureAction, FetchUpdateStudiesSuccessAction
} from "../../../action/studies/studies-update";


export function* updateStudiesSagaCall(action: FetchUpdateStudiesAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.updateStudies, action.payload);

        yield put<FetchUpdateStudiesSuccessAction>({
            type: FETCH_UPDATE_STUDIES_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchUpdateStudiesFailureAction>({
            type: FETCH_UPDATE_STUDIES_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* updateStudiesSaga(): SagaIterator {
    yield takeLatest(FETCH_UPDATE_STUDIES, updateStudiesSagaCall)
}