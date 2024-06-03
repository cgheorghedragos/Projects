import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {
    FETCH_UPDATE_EXPERIENCE,
    FETCH_UPDATE_EXPERIENCE_FAILURE,
    FETCH_UPDATE_EXPERIENCE_SUCCESS,
    FetchUpdateExperienceAction,
    FetchUpdateExperienceFailureAction,
    FetchUpdateExperienceSuccessAction
} from "../../../action/experience/experience-update";
import {RESTClientService} from "../../../../httpclient";


export function* updateExperienceSagaCall(action: FetchUpdateExperienceAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.updateExperience, action.payload);

        yield put<FetchUpdateExperienceSuccessAction>({
            type: FETCH_UPDATE_EXPERIENCE_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchUpdateExperienceFailureAction>({
            type: FETCH_UPDATE_EXPERIENCE_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* updateExperienceSaga(): SagaIterator {
    yield takeLatest(FETCH_UPDATE_EXPERIENCE, updateExperienceSagaCall)
}