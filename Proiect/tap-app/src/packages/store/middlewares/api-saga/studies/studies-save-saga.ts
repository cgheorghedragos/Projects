import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {
    FETCH_SAVE_STUDIES,
    FETCH_SAVE_STUDIES_FAILURE,
    FETCH_SAVE_STUDIES_SUCCESS,
    FetchSaveStudiesAction,
    FetchSaveStudiesFailureAction,
    FetchSaveStudiesSuccessAction
} from "../../../action/studies/studies-save";


export function* saveStudiesSagaCall(action: FetchSaveStudiesAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.addStudies, action.payload);

        yield put<FetchSaveStudiesSuccessAction>({
            type: FETCH_SAVE_STUDIES_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchSaveStudiesFailureAction>({
            type: FETCH_SAVE_STUDIES_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* saveStudiesSaga(): SagaIterator {
    yield takeLatest(FETCH_SAVE_STUDIES, saveStudiesSagaCall)
}