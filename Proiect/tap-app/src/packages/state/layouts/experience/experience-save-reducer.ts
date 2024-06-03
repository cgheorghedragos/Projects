import {
    FETCH_SAVE_EXPERIENCE_FAILURE,
    FETCH_SAVE_EXPERIENCE_SUCCESS,
    FetchSaveExperienceAction,
    FetchSaveExperienceFailureAction,
    FetchSaveExperienceSuccessAction
} from "../../../store/action/experience/experience-save";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchSaveExperienceAction
    | FetchSaveExperienceSuccessAction
    | FetchSaveExperienceFailureAction

const INITIAL_STATE: ExperienceDTO = {
    id: null,
    isPersonalProject: false,
    startDate: null,
    endDate: null,
    description: '',
    user: null,
    photos: null,
    location: ''
}

export default (state: ExperienceDTO = INITIAL_STATE, action: ActionTypes): ExperienceDTO => {
    switch (action.type) {
        case FETCH_SAVE_EXPERIENCE_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_SAVE_EXPERIENCE_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}