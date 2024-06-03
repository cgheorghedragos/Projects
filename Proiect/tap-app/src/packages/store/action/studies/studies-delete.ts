import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_DELETE_STUDIES = "NETWORK/DELETE_STUDIES";
export const FETCH_DELETE_STUDIES_SUCCESS = "NETWORK/DELETE_STUDIES_SUCCESS";
export const FETCH_DELETE_STUDIES_FAILURE = "NETWORK/DELETE_STUDIES_FAILURE";

export type FetchDeleteStudiesAction = {
    type: typeof FETCH_DELETE_STUDIES;
    payload: number
}

export type FetchDeleteStudiesSuccessAction = {
    type: typeof FETCH_DELETE_STUDIES_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchDeleteStudiesFailureAction = {
    type: typeof FETCH_DELETE_STUDIES_FAILURE;
    payload: string;
}