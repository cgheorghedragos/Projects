import {DispatchProps, StateProps} from "./props";
import {FETCH_USER_LOGIN_SUCCESS, FetchUserLoginSuccessAction} from "../../packages/store/action/user-login";
import {getLoginState} from "../../packages/state/layouts/userLogin/user-login-selectors";
import {ApplicationState} from "../../packages/types/ApplicationState";

export const mapStateToProps = (state: ApplicationState): StateProps => {
    const userLogin = getLoginState(state)
    return {
        userLogin
    };
};
const dispatchSuccessLogout = (): FetchUserLoginSuccessAction => ({
    type: FETCH_USER_LOGIN_SUCCESS,
    payload: {access_token: "", refresh_token: ""}
})

export const mapDispatchToProps = (): DispatchProps => {
    return {
        dispatchSuccessLogout,
    };
};