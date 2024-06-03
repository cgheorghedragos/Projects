import {
    FETCH_UPDATE_EXPERIENCE_FAILURE,
    FETCH_UPDATE_EXPERIENCE_SUCCESS,
    FetchUpdateExperienceAction,
    FetchUpdateExperienceFailureAction,
    FetchUpdateExperienceSuccessAction
} from "../../../store/action/experience/experience-update";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchUpdateExperienceAction
    | FetchUpdateExperienceSuccessAction
    | FetchUpdateExperienceFailureAction

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
        case FETCH_UPDATE_EXPERIENCE_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_UPDATE_EXPERIENCE_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}