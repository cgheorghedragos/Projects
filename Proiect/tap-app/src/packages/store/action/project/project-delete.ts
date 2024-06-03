import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_DELETE_PROJECT = "NETWORK/DELETE_PROJECT";
export const FETCH_DELETE_PROJECT_SUCCESS = "NETWORK/DELETE_PROJECT_SUCCESS";
export const FETCH_DELETE_PROJECT_FAILURE = "NETWORK/DELETE_PROJECT_FAILURE";

export type FetchDeleteProjectAction = {
    type: typeof FETCH_DELETE_PROJECT;
    payload: number
}

export type FetchDeleteProjectSuccessAction = {
    type: typeof FETCH_DELETE_PROJECT_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchDeleteProjectFailureAction = {
    type: typeof FETCH_DELETE_PROJECT_FAILURE;
    payload: string;
}