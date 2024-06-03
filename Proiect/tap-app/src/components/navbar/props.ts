import {FetchUserLoginSuccessAction} from "../../packages/store/action/user-login";
import {FetchSearchUserDetailsAction} from "../../packages/store/action/user-search";
import {LoginLayout} from "../../packages/types/layouts/UserDetailsLayout";

export type StateProps = {
    userLogin: LoginLayout;
};
export type DispatchProps = {
    dispatchSuccessLogout(): FetchUserLoginSuccessAction
};

export type NavbarProps = StateProps & DispatchProps;