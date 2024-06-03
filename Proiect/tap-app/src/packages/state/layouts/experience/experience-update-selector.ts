import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getUpdatedExperience = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.experienceUpdate;
}