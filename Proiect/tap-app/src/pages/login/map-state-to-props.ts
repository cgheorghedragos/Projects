import {DispatchProps, StateProps} from "./props";
import {FETCH_USER_LOGIN, FetchUserLoginAction} from "../../packages/store/action/user-login";
import {LoginLayout, UserDetailsPayload} from "../../packages/types/layouts/UserDetailsLayout";
import {getLoginState} from "../../packages/state/layouts/userLogin/user-login-selectors";
import {ApplicationState} from "../../packages/types/ApplicationState";
import {useSelector} from "react-redux";
import applicationStore from "../../packages/store";

export const mapStateToProps = (state: ApplicationState): StateProps => {
    const loginLayout: LoginLayout = getLoginState(state)
    console.log(loginLayout)
    return {userLoginState: loginLayout};
};

const dispatchUserLogin = (userCredentials: UserDetailsPayload): FetchUserLoginAction => ({
    type: FETCH_USER_LOGIN,
    payload: userCredentials
})

export const mapDispatchToProps: DispatchProps = {
    dispatchUserLogin
}