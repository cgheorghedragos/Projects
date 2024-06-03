import {SagaIterator} from "redux-saga";
import {all, call, put, takeLatest} from "redux-saga/effects";

import {RESTClientService} from "../../../../httpclient";
import {
    FETCH_SAVE_PROJECT,
    FETCH_SAVE_PROJECT_FAILURE,
    FETCH_SAVE_PROJECT_SUCCESS,
    FetchSaveProjectAction,
    FetchSaveProjectFailureAction,
    FetchSaveProjectSuccessAction
} from "../../../action/project/project-save";
import {PhotoDTO, UploadPhotoDTO, UploadPhotoReq} from "../../../../types/layouts/PhotosLayout";
import {base64ToFileSync} from "../../../../util/FileParser";


export function* saveProjectSagaCall(action: FetchSaveProjectAction): SagaIterator {
    try {

        // Filter and prepare the photos data
        const photos = action.payload.photos?.filter(photo => photo.file != undefined);

        // If no photos are available, return early
        if (!photos || photos.length === 0) {
            return;
        }

        // Make multiple asynchronous calls
        const uploadedPhotos: UploadPhotoDTO[] = yield all(
            photos.map(photo => {
                const file = base64ToFileSync(photo.file!, "filename");
                let photoReq: UploadPhotoReq = {file: file}
                return call(RESTClientService.uploadPhoto, photoReq);
            })
        );

        const payload = action.payload;
        payload.photos = uploadedPhotos.map(photo => {
            let photoDTO: PhotoDTO = {photoUrl: photo.photo_url};
            return photoDTO;
        })


        const response = yield call(RESTClientService.addExperience, action.payload);

        yield put<FetchSaveProjectSuccessAction>({
            type: FETCH_SAVE_PROJECT_SUCCESS,
            payload: response
        });

    } catch (errors) {
        yield put<FetchSaveProjectFailureAction>({
            type: FETCH_SAVE_PROJECT_FAILURE,
            payload: "FAILURE"
        });
    }
}

export default function* saveProjectSaga(): SagaIterator {
    yield takeLatest(FETCH_SAVE_PROJECT, saveProjectSagaCall)
}