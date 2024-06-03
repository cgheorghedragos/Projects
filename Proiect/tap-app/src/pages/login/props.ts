import {FetchUserLoginAction} from "../../packages/store/action/user-login";
import {FetchUserDetailsAction} from "../../packages/store/action/user-details";
import {LoginLayout, UserDetailsPayload} from "../../packages/types/layouts/UserDetailsLayout";

export type StateProps = {
    userLoginState: LoginLayout
};
export type DispatchProps = {
    dispatchUserLogin: (userCredentials : UserDetailsPayload) => FetchUserLoginAction
};

export type LoginPageProps = StateProps & DispatchProps;