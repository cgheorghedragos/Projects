import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_UPDATE_STUDIES = "NETWORK/UPDATE_STUDIES";
export const FETCH_UPDATE_STUDIES_SUCCESS = "NETWORK/UPDATE_STUDIES_SUCCESS";
export const FETCH_UPDATE_STUDIES_FAILURE = "NETWORK/UPDATE_STUDIES_FAILURE";

export type FetchUpdateStudiesAction = {
    type: typeof FETCH_UPDATE_STUDIES;
    payload: ExperienceDTO
}

export type FetchUpdateStudiesSuccessAction = {
    type: typeof FETCH_UPDATE_STUDIES_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchUpdateStudiesFailureAction = {
    type: typeof FETCH_UPDATE_STUDIES_FAILURE;
    payload: string;
}