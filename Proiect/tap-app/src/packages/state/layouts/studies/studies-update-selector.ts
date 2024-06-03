import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getUpdatedStudies = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.studiesUpdate;
}