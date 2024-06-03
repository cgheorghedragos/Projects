import {ApplicationState} from "../../../types/ApplicationState";
import {UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";

export const getUserDetails = (state: ApplicationState): UserDetailResponse => {
    return state.layouts.userDetails;
}