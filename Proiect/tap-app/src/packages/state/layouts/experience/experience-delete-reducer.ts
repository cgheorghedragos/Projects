import {
    FETCH_DELETE_EXPERIENCE_FAILURE,
    FETCH_DELETE_EXPERIENCE_SUCCESS,
    FetchDeleteExperienceAction,
    FetchDeleteExperienceFailureAction,
    FetchDeleteExperienceSuccessAction
} from "../../../store/action/experience/experience-delete";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchDeleteExperienceAction
    | FetchDeleteExperienceSuccessAction
    | FetchDeleteExperienceFailureAction

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
        case FETCH_DELETE_EXPERIENCE_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_DELETE_EXPERIENCE_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}