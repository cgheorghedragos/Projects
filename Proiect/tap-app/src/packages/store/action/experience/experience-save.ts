import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const FETCH_SAVE_EXPERIENCE = "NETWORK/SAVE_EXPERIENCE";
export const FETCH_SAVE_EXPERIENCE_SUCCESS = "NETWORK/SAVE_EXPERIENCE_SUCCESS";
export const FETCH_SAVE_EXPERIENCE_FAILURE = "NETWORK/SAVE_EXPERIENCE_FAILURE";

export type FetchSaveExperienceAction = {
    type: typeof FETCH_SAVE_EXPERIENCE;
    payload: ExperienceDTO
}

export type FetchSaveExperienceSuccessAction = {
    type: typeof FETCH_SAVE_EXPERIENCE_SUCCESS;
    payload: ExperienceDTO;
}

export type FetchSaveExperienceFailureAction = {
    type: typeof FETCH_SAVE_EXPERIENCE_FAILURE;
    payload: string;
}