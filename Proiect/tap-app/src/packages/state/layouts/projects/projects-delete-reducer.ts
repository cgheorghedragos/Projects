import {
    FETCH_DELETE_PROJECT_FAILURE,
    FETCH_DELETE_PROJECT_SUCCESS,
    FetchDeleteProjectAction,
    FetchDeleteProjectFailureAction,
    FetchDeleteProjectSuccessAction
} from "../../../store/action/project/project-delete";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchDeleteProjectAction
    | FetchDeleteProjectSuccessAction
    | FetchDeleteProjectFailureAction

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
        case FETCH_DELETE_PROJECT_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_DELETE_PROJECT_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}