import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_UPDATE_EXPERIENCE = "NETWORK/UPDATE_EXPERIENCE";
export const FETCH_UPDATE_EXPERIENCE_SUCCESS = "NETWORK/UPDATE_EXPERIENCE_SUCCESS";
export const FETCH_UPDATE_EXPERIENCE_FAILURE = "NETWORK/UPDATE_EXPERIENCE_FAILURE";

export type FetchUpdateExperienceAction = {
    type: typeof FETCH_UPDATE_EXPERIENCE;
    payload: ExperienceDTO
}

export type FetchUpdateExperienceSuccessAction = {
    type: typeof FETCH_UPDATE_EXPERIENCE_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchUpdateExperienceFailureAction = {
    type: typeof FETCH_UPDATE_EXPERIENCE_FAILURE;
    payload: string;
}