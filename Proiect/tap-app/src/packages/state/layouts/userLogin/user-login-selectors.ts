import {ApplicationState} from "../../../types/ApplicationState";
import {LoginLayout, UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";

export const getAccessToken = (state: ApplicationState): string => {
    return state.layouts.userLogin.userLogin.access_token;
}

export const getLoginState = (state: ApplicationState) => state.layouts.userLogin;
