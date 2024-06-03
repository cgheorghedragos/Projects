import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getDeletedProjects = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.projectDelete;
}