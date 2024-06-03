import {UserRegisterLayout} from "../../../types/layouts/UserDetailsLayout";
import {
    FETCH_USER_REGISTER_FAILURE,
    FETCH_USER_REGISTER_SUCCESS,
    FetchUserRegisterAction,
    FetchUserRegisterFailureAction,
    FetchUserRegisterSuccessAction
} from "../../../store/action/user-register";

type ActionTypes =
    | FetchUserRegisterAction
    | FetchUserRegisterSuccessAction
    | FetchUserRegisterFailureAction

const INITIAL_STATE: UserRegisterLayout = {
    error: null,
    userRegister: {
        id: null,
        username: null,
        photoUrl: null,
        friends: null,
        pendingFriends: null,
        experiences: null,
        studies: null,
        isPersonalAccount: false
    }
}

export default (state: UserRegisterLayout = INITIAL_STATE, action: ActionTypes): UserRegisterLayout => {
    switch (action.type) {
        case FETCH_USER_REGISTER_SUCCESS: {
            return {
                ...state,
                error: null,
                userRegister: action.payload
            };
        }
        case FETCH_USER_REGISTER_FAILURE: {
            return {
                ...state,
                error: action.payload
            }
        }
        default:
            return state;
    }
}