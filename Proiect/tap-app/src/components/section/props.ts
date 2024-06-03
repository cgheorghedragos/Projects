import {FetchSaveExperienceAction} from "../../packages/store/action/experience/experience-save";
import {ExperienceDTO} from "../../packages/types/layouts/ExperienceLayout";
import {FetchUpdateExperienceAction} from "../../packages/store/action/experience/experience-update";
import {FetchDeleteExperienceAction} from "../../packages/store/action/experience/experience-delete";
import {FetchSaveStudiesAction} from "../../packages/store/action/studies/studies-save";
import {FetchUpdateStudiesAction} from "../../packages/store/action/studies/studies-update";
import {FetchDeleteStudiesAction} from "../../packages/store/action/studies/studies-delete";
import {FetchSaveProjectAction} from "../../packages/store/action/project/project-save";

export type OwnProps = {
    sectionTitle: string | null;
    studies: ExperienceDTO[] | null;
    experience: ExperienceDTO[] | null;
    projects: ExperienceDTO[] | null;
    isPersonalAccount: boolean;
}

export type StateProps = {};

export type DispatchProps = {
    dispatchSaveExperience: (experienceDTO: ExperienceDTO) => FetchSaveExperienceAction,
    dispatchUpdateExperience: (experienceDTO: ExperienceDTO) => FetchUpdateExperienceAction,
    dispatchDeleteExperience: (id: number) => FetchDeleteExperienceAction,
    dispatchSaveStudies: (experienceDTO: ExperienceDTO) => FetchSaveStudiesAction,
    dispatchUpdateStudies: (experienceDTO: ExperienceDTO) => FetchUpdateStudiesAction,
    dispatchDeleteStudies: (id: number) => FetchDeleteStudiesAction,
    dispatchSaveProject: (experienceDTO: ExperienceDTO) => FetchSaveProjectAction,
};

export type SectionProps =  DispatchProps & OwnProps & StateProps;