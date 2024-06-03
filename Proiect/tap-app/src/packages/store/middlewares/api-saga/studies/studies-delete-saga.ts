import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {
    FETCH_DELETE_STUDIES,
    FETCH_DELETE_STUDIES_FAILURE,
    FETCH_DELETE_STUDIES_SUCCESS,
    FetchDeleteStudiesAction,
    FetchDeleteStudiesFailureAction,
    FetchDeleteStudiesSuccessAction
} from "../../../action/studies/studies-delete";
import {ExperienceDTO} from "../../../../types/layouts/ExperienceLayout";


export function* deleteStudiesSagaCall(action: FetchDeleteStudiesAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.deleteStudies, action.payload);
        const experienceDTO : ExperienceDTO = {id : Math.random()}

        yield put<FetchDeleteStudiesSuccessAction>({
            type: FETCH_DELETE_STUDIES_SUCCESS,
            payload: experienceDTO
        });

    } catch (errors) {
        yield put<FetchDeleteStudiesFailureAction>({
            type: FETCH_DELETE_STUDIES_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* deleteStudiesSaga(): SagaIterator {
    yield takeLatest(FETCH_DELETE_STUDIES, deleteStudiesSagaCall)
}