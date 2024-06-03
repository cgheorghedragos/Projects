import {
    FETCH_DELETE_STUDIES_FAILURE,
    FETCH_DELETE_STUDIES_SUCCESS,
    FetchDeleteStudiesAction,
    FetchDeleteStudiesFailureAction,
    FetchDeleteStudiesSuccessAction
} from "../../../store/action/studies/studies-delete";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchDeleteStudiesAction
    | FetchDeleteStudiesSuccessAction
    | FetchDeleteStudiesFailureAction

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
        case FETCH_DELETE_STUDIES_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_DELETE_STUDIES_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}