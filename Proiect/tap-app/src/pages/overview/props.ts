import {UserDetailResponse} from "../../packages/types/layouts/UserDetailsLayout";
import {FetchUserDetailsAction} from "../../packages/store/action/user-details";

export type StateProps = {
    userDetails: UserDetailResponse,
    changesDone: number | null
};
export type DispatchProps = {
    dispatchGetUserDetails: (user: string | null) => FetchUserDetailsAction
};

export type OverviewPageProps = StateProps & DispatchProps;