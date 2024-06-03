import {
    FETCH_SAVE_PROJECT_FAILURE,
    FETCH_SAVE_PROJECT_SUCCESS,
    FetchSaveProjectAction,
    FetchSaveProjectFailureAction,
    FetchSaveProjectSuccessAction
} from "../../../store/action/project/project-save";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchSaveProjectAction
    | FetchSaveProjectSuccessAction
    | FetchSaveProjectFailureAction

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
        case FETCH_SAVE_PROJECT_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_SAVE_PROJECT_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}