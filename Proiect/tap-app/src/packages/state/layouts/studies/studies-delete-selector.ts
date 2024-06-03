import {ApplicationState} from "../../../types/ApplicationState";
import {ExperienceDTO} from "../../../types/layouts/ExperienceLayout";

export const getDeletedStudies = (state: ApplicationState): ExperienceDTO => {
    return state.layouts.studiesDelete;
}