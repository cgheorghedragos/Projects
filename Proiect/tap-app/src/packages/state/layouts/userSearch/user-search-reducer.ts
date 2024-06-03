import {
    FETCH_SEARCH_USER_DETAILS_FAILURE,
    FETCH_SEARCH_USER_DETAILS_SUCCESS,
    FetchSearchUserDetailsAction,
    FetchSearchUserDetailsFailureAction,
    FetchSearchUserDetailsSuccessAction
} from "../../../store/action/user-search";
import {UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";

type ActionTypes = 
| FetchSearchUserDetailsAction
| FetchSearchUserDetailsSuccessAction
| FetchSearchUserDetailsFailureAction

const INITIAL_STATE: UserDetailResponse[] = [{
    id: null,
    username: null,
    photoUrl: null,
    friends: null,
    pendingFriends: null,
    experiences: null,
    studies: null,
    isPersonalAccount: false,
}];

export default (state: UserDetailResponse[] = INITIAL_STATE, action: ActionTypes): UserDetailResponse[] => {
    switch(action.type) {
        case FETCH_SEARCH_USER_DETAILS_SUCCESS: {
            return {...action.payload};
        }
        case FETCH_SEARCH_USER_DETAILS_FAILURE: {
            return {
                ...state
            }
        }
        default: return state;
    }
}