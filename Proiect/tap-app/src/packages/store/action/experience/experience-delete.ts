import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_DELETE_EXPERIENCE = "NETWORK/DELETE_EXPERIENCE";
export const FETCH_DELETE_EXPERIENCE_SUCCESS = "NETWORK/DELETE_EXPERIENCE_SUCCESS";
export const FETCH_DELETE_EXPERIENCE_FAILURE = "NETWORK/DELETE_EXPERIENCE_FAILURE";

export type FetchDeleteExperienceAction = {
    type: typeof FETCH_DELETE_EXPERIENCE;
    payload: number
}

export type FetchDeleteExperienceSuccessAction = {
    type: typeof FETCH_DELETE_EXPERIENCE_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchDeleteExperienceFailureAction = {
    type: typeof FETCH_DELETE_EXPERIENCE_FAILURE;
    payload: string;
}