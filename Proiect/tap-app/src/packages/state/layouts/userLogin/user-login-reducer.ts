import {
    FETCH_USER_LOGIN_FAILURE,
    FETCH_USER_LOGIN_SUCCESS,
    FetchUserLoginAction,
    FetchUserLoginFailureAction,
    FetchUserLoginSuccessAction
} from "../../../store/action/user-login";
import {LoginLayout} from "../../../types/layouts/UserDetailsLayout";

type ActionTypes =
    | FetchUserLoginAction
    | FetchUserLoginSuccessAction
    | FetchUserLoginFailureAction

const INITIAL_STATE: LoginLayout = {
    error: null,
    userLogin: {
        access_token: localStorage.getItem("auth") || "",
        refresh_token: ""
    }
}

export default (state: LoginLayout = INITIAL_STATE, action: ActionTypes): LoginLayout => {
    switch (action.type) {
        case FETCH_USER_LOGIN_SUCCESS: {
            localStorage.setItem('auth', action.payload.access_token);
            return {
                ...state,
                error: null,
                userLogin: action.payload
            };
        }
        case FETCH_USER_LOGIN_FAILURE: {
            return {
                ...state,
                error: action.payload
            }
        }
        default:
            return state;
    }
}