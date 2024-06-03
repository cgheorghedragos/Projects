import {
    FETCH_SAVE_STUDIES_FAILURE,
    FETCH_SAVE_STUDIES_SUCCESS,
    FetchSaveStudiesAction,
    FetchSaveStudiesFailureAction,
    FetchSaveStudiesSuccessAction
} from "../../../store/action/studies/studies-save";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchSaveStudiesAction
    | FetchSaveStudiesSuccessAction
    | FetchSaveStudiesFailureAction

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
        case FETCH_SAVE_STUDIES_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_SAVE_STUDIES_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}