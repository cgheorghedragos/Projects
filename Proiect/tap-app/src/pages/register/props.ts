import {UserDetailsPayload, UserRegisterLayout} from "../../packages/types/layouts/UserDetailsLayout";
import {FetchUserRegisterAction} from "../../packages/store/action/user-register";

export type StateProps = {
    handleLogin: () => void;
    userRegisteredState: UserRegisterLayout;
};
export type DispatchProps = {
    dispatchUserRegister: (userCredentials: UserDetailsPayload) => FetchUserRegisterAction;
};

export type RegisterPageProps = StateProps & DispatchProps;