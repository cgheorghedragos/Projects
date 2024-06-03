import {
    FETCH_UPDATE_STUDIES_FAILURE,
    FETCH_UPDATE_STUDIES_SUCCESS,
    FetchUpdateStudiesAction,
    FetchUpdateStudiesFailureAction,
    FetchUpdateStudiesSuccessAction
} from "../../../store/action/studies/studies-update";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

type ActionTypes =
    | FetchUpdateStudiesAction
    | FetchUpdateStudiesSuccessAction
    | FetchUpdateStudiesFailureAction

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
        case FETCH_UPDATE_STUDIES_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_UPDATE_STUDIES_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}