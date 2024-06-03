import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getUpdatedProjects = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.projectUpdate;
}