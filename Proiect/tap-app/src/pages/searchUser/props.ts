import {UserDetailResponse} from "../../packages/types/layouts/UserDetailsLayout";
import {FetchSearchUserDetailsAction} from "../../packages/store/action/user-search";

export type StateProps = {
    users: UserDetailResponse[];
};
export type DispatchProps = {
    dispatchSearchUsers: (username: string) => FetchSearchUserDetailsAction
};

export type SearchPageProps = StateProps & DispatchProps;