import {API} from "../request/api";
import {deleteRequest, getRequest, postRequest, putRequest} from "../request/request";

function RESTClientService() {
    async function getUserDetailsReq(payload) {
        return getRequest(API.FIND_USER, {params: {username: payload.username}});
    }

    async function loginUser(payload) {
        return postRequest(API.LOGIN, {data: payload});
    }

    async function registerUser(payload) {
        return postRequest(API.REGISTER_USER, {data: payload});
    }

    async function getSearchUserDetailsReq(payload) {
        return getRequest(API.SEARCH_USER_DETAILS, {params: {username: payload.username}});
    }

    async function addExperienceReq(payload) {
        return postRequest(API.EXPERIENCE_SAVE, {data: payload});
    }

    async function updateExperienceReq(payload) {
        return putRequest(API.EXPERIENCE_UPDATE, {data: payload});
    }

    async function deleteExperienceReq(payload) {
        return deleteRequest(API.EXPERIENCE_DELETE, {params: {id : payload}});
    }

    async function addStudiesReq(payload) {
        return postRequest(API.STUDIES_SAVE, {data: payload});
    }

    async function updateStudiesReq(payload) {
        return putRequest(API.STUDIES_UPDATE, {data: payload});
    }

    async function deleteStudiesReq(payload) {
        return deleteRequest(API.STUDIES_DELETE, {params: {id : payload}});
    }

    async function addProjectReq(payload) {
        return postRequest(API.PROJECT_SAVE, {data: payload});
    }

    async function updateProjectReq(payload) {
        return putRequest(API.PROJECT_UPDATE, {data: payload});
    }

    async function deleteProjectReq(payload) {
        return deleteRequest(API.PROJECT_DELETE, {params: {id : payload}});
    }

    async function uploadPhotoReq(payload) {
        const formData = new FormData();
        formData.append('file', payload.file)

        return postRequest(API.UPLOAD_PHOTO, {params: {fileName: payload.fileName}, data: formData});
    }

    return {
        getUserDetailsReq,
        getSearchUserDetailsReq,
        loginUser,
        registerUser,
        addExperienceReq,
        updateExperienceReq,
        deleteExperienceReq,
        addStudiesReq,
        updateStudiesReq,
        deleteStudiesReq,
        uploadPhotoReq,
        addProjectReq,
        deleteProjectReq,
        updateProjectReq
    }
}

export {RESTClientService}