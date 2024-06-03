import {UserDetailsPayload, UserLoginResponse} from "../../types/layouts/UserDetailsLayout";

export const FETCH_USER_LOGIN = "NETWORK/FETCH_USER_LOGIN";
export const FETCH_USER_LOGIN_SUCCESS = "NETWORK/FETCH_USER_LOGIN_SUCCESS";
export const FETCH_USER_LOGIN_FAILURE = "NETWORK/FETCH_USER_LOGIN_FAILURE";


export type FetchUserLoginAction = {
    type: typeof FETCH_USER_LOGIN;
    payload: UserDetailsPayload;
}

export type FetchUserLoginSuccessAction = {
    type: typeof FETCH_USER_LOGIN_SUCCESS;
    payload: UserLoginResponse;
}

export type FetchUserLoginFailureAction = {
    type: typeof FETCH_USER_LOGIN_FAILURE;
    payload: string;
}