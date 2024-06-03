import {DispatchProps, StateProps} from "./props";
import {getUserDetails} from "../../packages/state/layouts/userDetails/user-details-selectors";
import {ApplicationState} from "../../packages/types/ApplicationState";
import {FETCH_USER_DETAILS, FetchUserDetailsAction} from "../../packages/store/action/user-details";
import {getSavedStudies} from "../../packages/state/layouts/./././studies/studies-save-selectiors";
import {getSavedExperience} from "../../packages/state/layouts/experience/experience-save-selectiors";
import {getSavedProjects} from "../../packages/state/layouts/projects/projects-save-selectiors";
import {getUpdatedProjects} from "../../packages/state/layouts/projects/projects-update-selector";
import {getUpdatedExperience} from "../../packages/state/layouts/experience/experience-update-selector";
import {getUpdatedStudies} from "../../packages/state/layouts/studies/studies-update-selector";
import {getDeletedStudies} from "../../packages/state/layouts/studies/studies-delete-selector";
import {getDeletedExperience} from "../../packages/state/layouts/experience/experience-delete-selector";
import {getDeletedProjects} from "../../packages/state/layouts/projects/projects-delete-selector";

export const mapStateToProps = (state: ApplicationState): StateProps => {
    const userDetails = getUserDetails(state)
    const savedStudiesID = getSavedStudies(state)?.id || null;
    const savedExperienceID = getSavedExperience(state)?.id || null;
    const savedProjectID = getSavedProjects(state)?.id || null;
    const updatedStudiesID = getUpdatedStudies(state)?.id || null;
    const updatedExperienceID = getUpdatedExperience(state)?.id || null;
    const updatedProjectID = getUpdatedProjects(state)?.id || null;
    const deletedStudies = getDeletedStudies(state)?.id || null;
    const deletedExperience = getDeletedExperience(state)?.id || null;
    const deletedProject = getDeletedProjects(state)?.id || null;

    const changesDone = savedStudiesID || savedExperienceID || savedProjectID || updatedProjectID || updatedExperienceID || updatedStudiesID || deletedStudies || deletedExperience || deletedProject;
    return {
        userDetails,
        changesDone,
    };
};

const dispatchGetUserDetails = (user: string | null): FetchUserDetailsAction => ({
    type: FETCH_USER_DETAILS,
    payload: {username: user ? user : "", password: ""}
})

export const mapDispatchToProps: DispatchProps = {
    dispatchGetUserDetails
}