import {SagaIterator} from "redux-saga";
import {call, put, takeLatest} from "redux-saga/effects";
import {
    FETCH_UPDATE_PROJECT,
    FETCH_UPDATE_PROJECT_FAILURE,
    FETCH_UPDATE_PROJECT_SUCCESS,
    FetchUpdateProjectAction,
    FetchUpdateProjectFailureAction,
    FetchUpdateProjectSuccessAction
} from "../../../action/project/project-update";
import {RESTClientService} from "../../../../httpclient";


export function* updateProjectSagaCall(action: FetchUpdateProjectAction): SagaIterator {
    try {
        const response = yield call(RESTClientService.updateProject, action.payload);

        yield put<FetchUpdateProjectSuccessAction>({
            type: FETCH_UPDATE_PROJECT_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchUpdateProjectFailureAction>({
            type: FETCH_UPDATE_PROJECT_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* updateProjectSaga(): SagaIterator {
    yield takeLatest(FETCH_UPDATE_PROJECT, updateProjectSagaCall)
}