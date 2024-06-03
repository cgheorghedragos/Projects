import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {
    FETCH_SAVE_EXPERIENCE,
    FETCH_SAVE_EXPERIENCE_FAILURE,
    FETCH_SAVE_EXPERIENCE_SUCCESS,
    FetchSaveExperienceAction,
    FetchSaveExperienceFailureAction,
    FetchSaveExperienceSuccessAction
} from "../../../action/experience/experience-save";


export function* saveExperienceSagaCall(action: FetchSaveExperienceAction): SagaIterator {
    try {

        const response = yield call(RESTClientService.addExperience, action.payload);

        yield put<FetchSaveExperienceSuccessAction>({
            type: FETCH_SAVE_EXPERIENCE_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchSaveExperienceFailureAction>({
            type: FETCH_SAVE_EXPERIENCE_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* saveExperienceSaga(): SagaIterator {
    yield takeLatest(FETCH_SAVE_EXPERIENCE, saveExperienceSagaCall)
}