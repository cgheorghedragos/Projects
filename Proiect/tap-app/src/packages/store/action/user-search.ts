import {UserDetailResponse, UserDetailsPayload} from "../../types/layouts/UserDetailsLayout";

export const FETCH_SEARCH_USER_DETAILS = "NETWORK/FETCH_SEARCH_USER_DETAILS";
export const FETCH_SEARCH_USER_DETAILS_SUCCESS = "NETWORK/FETCH_SEARCH_USER_DETAILS_SUCCESS";
export const FETCH_SEARCH_USER_DETAILS_FAILURE = "NETWORK/FETCH_SEARCH_USER_DETAILS_FAILURE";


export type FetchSearchUserDetailsAction = {
    type: typeof FETCH_SEARCH_USER_DETAILS;
    payload: UserDetailsPayload
}

export type FetchSearchUserDetailsSuccessAction = {
    type: typeof FETCH_SEARCH_USER_DETAILS_SUCCESS;
    payload: UserDetailResponse[];
}

export type FetchSearchUserDetailsFailureAction = {
    type: typeof FETCH_SEARCH_USER_DETAILS_FAILURE;
    payload: string;
}