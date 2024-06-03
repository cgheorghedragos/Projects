import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_UPDATE_PROJECT = "NETWORK/UPDATE_PROJECT";
export const FETCH_UPDATE_PROJECT_SUCCESS = "NETWORK/UPDATE_PROJECT_SUCCESS";
export const FETCH_UPDATE_PROJECT_FAILURE = "NETWORK/UPDATE_PROJECT_FAILURE";

export type FetchUpdateProjectAction = {
    type: typeof FETCH_UPDATE_PROJECT;
    payload: ExperienceDTO
}

export type FetchUpdateProjectSuccessAction = {
    type: typeof FETCH_UPDATE_PROJECT_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchUpdateProjectFailureAction = {
    type: typeof FETCH_UPDATE_PROJECT_FAILURE;
    payload: string;
}