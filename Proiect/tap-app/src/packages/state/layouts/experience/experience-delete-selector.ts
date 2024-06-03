import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getDeletedExperience = (state: ApplicationState): ExperienceDTO | null => {
    return state.layouts.experienceDelete;
}