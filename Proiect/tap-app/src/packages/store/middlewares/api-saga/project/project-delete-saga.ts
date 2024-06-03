import {SagaIterator} from "redux-saga";
import {all, call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {FetchSaveProjectAction} from "../../../action/project/project-save";
import {
    FETCH_DELETE_PROJECT,
    FETCH_DELETE_PROJECT_FAILURE,
    FETCH_DELETE_PROJECT_SUCCESS, FetchDeleteProjectAction,
    FetchDeleteProjectFailureAction,
    FetchDeleteProjectSuccessAction
} from "../../../action/project/project-delete";
import {PhotoDTO, UploadPhotoReq} from "../../../../types/layouts/PhotosLayout";
import {ExperienceDTO} from "../../../../types/layouts/ExperienceLayout";


export function* deleteProjectSagaCall(action: FetchDeleteProjectAction): SagaIterator {
    try {

        const response = yield call(RESTClientService.deleteProject, action.payload);
        const experienceDTO : ExperienceDTO = {id : Math.random()}

        yield put<FetchDeleteProjectSuccessAction>({
            type: FETCH_DELETE_PROJECT_SUCCESS,
            payload: experienceDTO
        });

    } catch (errors) {
        yield put<FetchDeleteProjectFailureAction>({
            type: FETCH_DELETE_PROJECT_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* deleteProjectSaga(): SagaIterator {
    yield takeLatest(FETCH_DELETE_PROJECT, deleteProjectSagaCall)
}