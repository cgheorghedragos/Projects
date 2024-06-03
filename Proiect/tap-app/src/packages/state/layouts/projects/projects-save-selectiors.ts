import {ApplicationState} from "../../../types/ApplicationState";
import {UserDetailResponse} from "../../../types/layouts/UserDetailsLayout";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getSavedProjects = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.projectSave;
}