import {FetchSaveExperienceAction} from "../../packages/store/action/experience/experience-save";
import {FetchUpdateExperienceAction} from "../../packages/store/action/experience/experience-update";
import {ExperienceDTO} from "../../packages/types/layouts/ExperienceLayout";
import {FetchSaveStudiesAction} from "../../packages/store/action/studies/studies-save";
import {FetchUpdateStudiesAction} from "../../packages/store/action/studies/studies-update";
import {FetchSaveProjectAction} from "../../packages/store/action/project/project-save";

export type OwnProps = {
    title?: string;
    onClose: () => void;
    onDispatch: (experienceDTO: ExperienceDTO) => FetchSaveExperienceAction | FetchUpdateExperienceAction | FetchSaveStudiesAction | FetchUpdateStudiesAction | FetchSaveProjectAction;
    locationName?: string;
    startDate?: Date | null;
    endDate?: Date | null;
    description?: string;
    isPersonalProject: boolean;
    id?: number | null;
    formatDate: (date :Date) => string;
};


export type DispatchProps = {};

export type DialogFormProps = OwnProps & DispatchProps;