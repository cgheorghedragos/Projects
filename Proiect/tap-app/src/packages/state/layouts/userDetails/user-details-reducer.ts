import {
    FETCH_USER_DETAILS_FAILURE,
    FETCH_USER_DETAILS_SUCCESS,
    FetchUserDetailsAction,
    FetchUserDetailsFailureAction,
    FetchUserDetailsSuccessAction
} from "../../../store/action/user-details";
import {UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";

type ActionTypes =
    | FetchUserDetailsAction
    | FetchUserDetailsSuccessAction
    | FetchUserDetailsFailureAction

const INITIAL_STATE: UserDetailResponse = {
    id: null,
    username: null,
    photoUrl: null,
    friends: null, pendingFriends: null,
    experiences: null,
    studies: null,
    isPersonalAccount: false
}

export default (state: UserDetailResponse = INITIAL_STATE, action: ActionTypes): UserDetailResponse => {
    switch (action.type) {
        case FETCH_USER_DETAILS_SUCCESS: {
            return {
                ...state,
                ...action.payload
            };
        }
        case FETCH_USER_DETAILS_FAILURE: {
            return {
                ...state
            }
        }
        default:
            return state;
    }
}