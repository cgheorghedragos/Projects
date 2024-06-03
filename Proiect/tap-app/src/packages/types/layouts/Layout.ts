import {LoginLayout, UserDetailResponse, UserRegisterLayout} from "./UserDetailsLayout";
import {ExperienceDTO} from "./ExperienceLayout";

export type Layout = {
    readonly userDetails: UserDetailResponse;
    readonly userSearch: UserDetailResponse[];
    readonly userLogin: LoginLayout;
    readonly experienceDTO: ExperienceDTO;
    readonly userRegister: UserRegisterLayout;
    readonly studiesDelete: ExperienceDTO;
    readonly studiesSave: ExperienceDTO;
    readonly studiesUpdate: ExperienceDTO;
    readonly experienceDelete: ExperienceDTO;
    readonly experienceUpdate: ExperienceDTO;
    readonly experienceSave: ExperienceDTO;
    readonly projectDelete: ExperienceDTO;
    readonly projectUpdate: ExperienceDTO;
    readonly projectSave: ExperienceDTO;
}