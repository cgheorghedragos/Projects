import {
    FETCH_UPDATE_PROJECT_FAILURE,
    FETCH_UPDATE_PROJECT_SUCCESS,
    FetchUpdateProjectAction,
    FetchUpdateProjectFailureAction,
    FetchUpdateProjectSuccessAction
} from "../../../store/action/project/project-update";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchUpdateProjectAction
    | FetchUpdateProjectSuccessAction
    | FetchUpdateProjectFailureAction

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
        case FETCH_UPDATE_PROJECT_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_UPDATE_PROJECT_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}