import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_SAVE_STUDIES = "NETWORK/SAVE_STUDIES";
export const FETCH_SAVE_STUDIES_SUCCESS = "NETWORK/SAVE_STUDIES_SUCCESS";
export const FETCH_SAVE_STUDIES_FAILURE = "NETWORK/SAVE_STUDIES_FAILURE";

export type FetchSaveStudiesAction = {
    type: typeof FETCH_SAVE_STUDIES;
    payload: ExperienceDTO
}

export type FetchSaveStudiesSuccessAction = {
    type: typeof FETCH_SAVE_STUDIES_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchSaveStudiesFailureAction = {
    type: typeof FETCH_SAVE_STUDIES_FAILURE;
    payload: string;
}