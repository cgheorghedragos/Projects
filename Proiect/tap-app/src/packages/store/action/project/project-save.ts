import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_SAVE_PROJECT = "NETWORK/SAVE_PROJECT";
export const FETCH_SAVE_PROJECT_SUCCESS = "NETWORK/SAVE_PROJECT_SUCCESS";
export const FETCH_SAVE_PROJECT_FAILURE = "NETWORK/SAVE_PROJECT_FAILURE";

export type FetchSaveProjectAction = {
    type: typeof FETCH_SAVE_PROJECT;
    payload: ExperienceDTO
}

export type FetchSaveProjectSuccessAction = {
    type: typeof FETCH_SAVE_PROJECT_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchSaveProjectFailureAction = {
    type: typeof FETCH_SAVE_PROJECT_FAILURE;
    payload: string;
}