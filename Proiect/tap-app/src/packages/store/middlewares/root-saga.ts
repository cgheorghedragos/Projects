import {SagaIterator} from "redux-saga";
import {all, AllEffect} from "redux-saga/effects";
import getUserDetailsSaga from "./api-saga/user-details-saga";
import getSearchUserDetailsSaga from "./api-saga/user-search-saga";
import userLoginSaga from "./api-saga/user-login-saga";
import saveExperienceSaga from "./api-saga/experience/experience-save-saga";
import updateExperienceSaga from "./api-saga/experience/experience-update-saga";
import deleteExperienceSaga from "./api-saga/experience/experience-delete-saga";
import saveStudiesSaga from "./api-saga/studies/studies-save-saga";
import updateStudiesSaga from "./api-saga/studies/studies-update-saga";
import deleteStudiesSaga from "./api-saga/studies/studies-delete-saga";
import saveProjectSaga from "./api-saga/project/project-save-saga";
import updateProjectSaga from "./api-saga/project/project-update-saga";
import deleteProjectSaga from "./api-saga/project/project-delete-saga";
import userRegisterSaga from "./api-saga/user-register-saga";


export default function* rootSaga(): IterableIterator<AllEffect<SagaIterator>> {
    yield all([
        saveExperienceSaga(),
        userLoginSaga(),
        getUserDetailsSaga(),
        getSearchUserDetailsSaga(),
        updateExperienceSaga(),
        deleteExperienceSaga(),
        saveStudiesSaga(),
        updateStudiesSaga(),
        deleteStudiesSaga(),
        saveProjectSaga(),
        updateProjectSaga(),
        deleteProjectSaga(),
        userRegisterSaga()
    ])
}