import {DispatchProps, StateProps} from "./props";
import {ApplicationState} from "../../packages/types/ApplicationState";
import {searchUsers} from "../../packages/state/layouts/userSearch/user-search-selectors";
import {FETCH_SEARCH_USER_DETAILS, FetchSearchUserDetailsAction} from "../../packages/store/action/user-search";

export const mapStateToProps = (state: ApplicationState): StateProps => {
    const users = searchUsers(state);
    console.log(users)
    return {
        users
    };
};

const dispatchSearchUsers = (username: string): FetchSearchUserDetailsAction => ({
    type: FETCH_SEARCH_USER_DETAILS,
    payload: {username: username, password: ""}
})

export const mapDispatchToProps = (): DispatchProps => {
    return {
        dispatchSearchUsers
    };
};