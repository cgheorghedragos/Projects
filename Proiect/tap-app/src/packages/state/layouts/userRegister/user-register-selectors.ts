import {ApplicationState} from "../../../types/ApplicationState";
import {LoginLayout, UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";



export const getRegisterState = (state: ApplicationState) => state.layouts.userRegister;
