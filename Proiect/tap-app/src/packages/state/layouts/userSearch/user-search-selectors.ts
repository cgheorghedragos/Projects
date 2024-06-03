import {ApplicationState} from "../../../types/ApplicationState";
import {UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";

export const searchUsers = (state: ApplicationState): UserDetailResponse[] => {
    return state.layouts.userSearch;
}