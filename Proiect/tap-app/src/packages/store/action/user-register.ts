import {UserDetailsPayload, UserDetailResponse} from "../../types/layouts/UserDetailsLayout";

export const FETCH_USER_REGISTER = "NETWORK/FETCH_USER_REGISTER";
export const FETCH_USER_REGISTER_SUCCESS = "NETWORK/FETCH_USER_REGISTER_SUCCESS";
export const FETCH_USER_REGISTER_FAILURE = "NETWORK/FETCH_USER_REGISTER_FAILURE";


export type FetchUserRegisterAction = {
    type: typeof FETCH_USER_REGISTER;
    payload: UserDetailsPayload;
}

export type FetchUserRegisterSuccessAction = {
    type: typeof FETCH_USER_REGISTER_SUCCESS;
    payload: UserDetailResponse;
}

export type FetchUserRegisterFailureAction = {
    type: typeof FETCH_USER_REGISTER_FAILURE;
    payload: string;
}