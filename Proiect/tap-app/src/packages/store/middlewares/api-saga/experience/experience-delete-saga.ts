import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {FetchSaveExperienceAction} from "../../../action/experience/experience-save";
import {
    FETCH_DELETE_EXPERIENCE,
    FETCH_DELETE_EXPERIENCE_FAILURE,
    FETCH_DELETE_EXPERIENCE_SUCCESS, FetchDeleteExperienceAction,
    FetchDeleteExperienceFailureAction,
    FetchDeleteExperienceSuccessAction
} from "../../../action/experience/experience-delete";
import {ExperienceDTO} from "../../../../types/layouts/ExperienceLayout";
import {FetchDeleteStudiesAction} from "../../../action/studies/studies-delete";


export function* deleteExperienceSagaCall(action: FetchDeleteExperienceAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.deleteExperience, action.payload);

        const experienceDTO : ExperienceDTO = {id : Math.random()}
        yield put<FetchDeleteExperienceSuccessAction>({
            type: FETCH_DELETE_EXPERIENCE_SUCCESS,
            payload: experienceDTO
        });

    } catch (errors) {
        yield put<FetchDeleteExperienceFailureAction>({
            type: FETCH_DELETE_EXPERIENCE_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* deleteExperienceSaga(): SagaIterator {
    yield takeLatest(FETCH_DELETE_EXPERIENCE, deleteExperienceSagaCall)
}