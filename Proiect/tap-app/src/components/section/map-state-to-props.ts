import {DispatchProps, StateProps} from "./props";
import {FETCH_SAVE_EXPERIENCE, FetchSaveExperienceAction} from "../../packages/store/action/experience/experience-save";
import {ExperienceDTO} from "../../packages/types/layouts/ExperienceLayout";
import {
    FETCH_DELETE_EXPERIENCE,
    FetchDeleteExperienceAction
} from "../../packages/store/action/experience/experience-delete";
import {
    FETCH_UPDATE_EXPERIENCE,
    FetchUpdateExperienceAction
} from "../../packages/store/action/experience/experience-update";
import {FETCH_DELETE_STUDIES, FetchDeleteStudiesAction} from "../../packages/store/action/studies/studies-delete";
import {FETCH_UPDATE_STUDIES, FetchUpdateStudiesAction} from "../../packages/store/action/studies/studies-update";
import {FETCH_SAVE_STUDIES, FetchSaveStudiesAction} from "../../packages/store/action/studies/studies-save";
import {FETCH_SAVE_PROJECT, FetchSaveProjectAction} from "../../packages/store/action/project/project-save";

export const mapStateToProps = (): StateProps => {
    return {};
};

const dispatchSaveExperience = (experienceDTO: ExperienceDTO): FetchSaveExperienceAction => ({
    type: FETCH_SAVE_EXPERIENCE,
    payload: experienceDTO
})

const dispatchUpdateExperience = (experienceDTO: ExperienceDTO): FetchUpdateExperienceAction => ({
    type: FETCH_UPDATE_EXPERIENCE,
    payload: experienceDTO
})

const dispatchDeleteExperience = (experienceID: number): FetchDeleteExperienceAction => ({
    type: FETCH_DELETE_EXPERIENCE,
    payload: experienceID
})

const dispatchSaveStudies = (experienceDTO: ExperienceDTO): FetchSaveStudiesAction => ({
    type: FETCH_SAVE_STUDIES,
    payload: experienceDTO
})

const dispatchSaveProject = (experienceDTO: ExperienceDTO): FetchSaveProjectAction => ({
    type: FETCH_SAVE_PROJECT,
    payload: experienceDTO
})

const dispatchUpdateStudies = (experienceDTO: ExperienceDTO): FetchUpdateStudiesAction => ({
    type: FETCH_UPDATE_STUDIES,
    payload: experienceDTO
})

const dispatchDeleteStudies = (experienceID: number): FetchDeleteStudiesAction => ({
    type: FETCH_DELETE_STUDIES,
    payload: experienceID
})

export const mapDispatchToProps = (): DispatchProps => {
    return {
        dispatchSaveExperience,
        dispatchDeleteExperience,
        dispatchUpdateExperience,
        dispatchSaveStudies,
        dispatchUpdateStudies,
        dispatchDeleteStudies,
        dispatchSaveProject,
    };
};