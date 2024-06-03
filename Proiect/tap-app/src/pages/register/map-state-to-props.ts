import {DispatchProps, StateProps} from "./props";
import {ApplicationState} from "../../packages/types/ApplicationState";
import {UserDetailsPayload, UserRegisterLayout} from "../../packages/types/layouts/UserDetailsLayout";
import {FETCH_USER_REGISTER, FetchUserRegisterAction} from "../../packages/store/action/user-register";
import {getLoginState} from "../../packages/state/layouts/userLogin/user-login-selectors";
import {getRegisterState} from "../../packages/state/layouts/userRegister/user-register-selectors";

export const mapStateToProps = (state: ApplicationState): StateProps => {
    const userRegisteredState = getRegisterState(state)
    const handleLogin = () => {
        window.location.href = '/login'
    };

    return {
        handleLogin,
        userRegisteredState
    };
};


const dispatchUserRegister = (userCredentials: UserDetailsPayload): FetchUserRegisterAction => ({
    type: FETCH_USER_REGISTER,
    payload: userCredentials
})

export const mapDispatchToProps = (): DispatchProps => {
    return {
        dispatchUserRegister
    };
};