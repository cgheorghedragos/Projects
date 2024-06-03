import {ExperienceDTO} from "../../packages/types/layouts/ExperienceLayout";
import {FetchSaveExperienceAction} from "../../packages/store/action/experience/experience-save";
import {FetchUpdateExperienceAction} from "../../packages/store/action/experience/experience-update";
import {FetchSaveStudiesAction} from "../../packages/store/action/studies/studies-save";
import {FetchUpdateStudiesAction} from "../../packages/store/action/studies/studies-update";
import {FetchSaveProjectAction} from "../../packages/store/action/project/project-save";
import {FetchDeleteProjectAction} from "../../packages/store/action/project/project-delete";
import {FetchDeleteStudiesAction} from "../../packages/store/action/studies/studies-delete";
import {FetchDeleteExperienceAction} from "../../packages/store/action/experience/experience-delete";

export type OwnProps = {
    id?: number
    title?: string;
    startDate: Date;
    endDate: Date;
    description?: string;
    isPersonalAccount: boolean
};

export type DispatchProps = {
    onUpdateDispatch: (experienceDTO: ExperienceDTO) => FetchSaveExperienceAction | FetchUpdateExperienceAction | FetchSaveStudiesAction | FetchUpdateStudiesAction | FetchSaveProjectAction;
    onDeleteDispatch: (id: number) => FetchDeleteProjectAction | FetchDeleteStudiesAction | FetchDeleteExperienceAction;
};

export type ExperienceProps = OwnProps & DispatchProps;