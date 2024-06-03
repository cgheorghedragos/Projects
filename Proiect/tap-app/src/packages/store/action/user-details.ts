import {UserDetailResponse, UserDetailsPayload} from "../../types/layouts/UserDetailsLayout";

export const FETCH_USER_DETAILS = "NETWORK/FETCH_USER_DETAILS";
export const FETCH_USER_DETAILS_SUCCESS = "NETWORK/FETCH_USER_DETAILS_SUCCESS";
export const FETCH_USER_DETAILS_FAILURE = "NETWORK/FETCH_USER_DETAILS_FAILURE";


export type FetchUserDetailsAction = {
    type: typeof FETCH_USER_DETAILS;
    payload: UserDetailsPayload
}

export type FetchUserDetailsSuccessAction = {
    type: typeof FETCH_USER_DETAILS_SUCCESS;
    payload: UserDetailResponse;
}

export type FetchUserDetailsFailureAction = {
    type: typeof FETCH_USER_DETAILS_FAILURE;
    payload: string;
}