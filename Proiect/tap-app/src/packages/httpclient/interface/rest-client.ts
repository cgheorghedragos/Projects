import {UserDetailResponse, UserDetailsPayload, UserLoginResponse} from "../../types/layouts/UserDetailsLayout";
import {RESTClientService} from "../clients/rest-client";
import {ExperienceDTO} from "../../types/layouts/ExperienceLayout";
import {UploadPhotoReq} from "../../types/layouts/PhotosLayout";

const client = RESTClientService();

export default {
    async getUserDetailsReq(payload: UserDetailsPayload): Promise<UserDetailResponse> {
        const result: UserDetailResponse = await client.getUserDetailsReq(payload)

        return result;
    },
    async getSearchUserDetailsReq(payload: UserDetailsPayload): Promise<UserDetailResponse[]> {
         const result = await client.getSearchUserDetailsReq(payload);

        return result;
    },
    async loginUser(payload: UserDetailsPayload): Promise<UserLoginResponse> {
        const result: UserLoginResponse = await client.loginUser(payload);

        return result;
    },
    async registerUser(payload: UserDetailsPayload): Promise<UserLoginResponse> {
        const result: UserLoginResponse = await client.registerUser(payload);

        return result;
    },
    async addExperience(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.addExperienceReq(payload);

        return result;
    },
    async updateExperience(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.updateExperienceReq(payload);

        return result;
    },
    async deleteExperience(payload: number): Promise<boolean> {
        const result: boolean = await client.deleteExperienceReq(payload);

        return result;
    },
    async addStudies(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.addStudiesReq(payload);

        return result;
    },
    async updateStudies(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.updateStudiesReq(payload);

        return result;
    },
    async deleteStudies(payload: number): Promise<boolean> {
        const result: boolean = await client.deleteStudiesReq(payload);

        return result;
    },
    async addProject(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.addProjectReq(payload);

        return result;
    },
    async updateProject(payload: ExperienceDTO): Promise<ExperienceDTO> {
        const result: ExperienceDTO = await client.updateProjectReq(payload);

        return result;
    },
    async deleteProject(payload: number): Promise<boolean> {
        const result: boolean = await client.deleteProjectReq(payload);

        return result;
    },
    async uploadPhoto(payload: UploadPhotoReq): Promise<string> {
        const result: string = await client.uploadPhotoReq(
            {fileName: Date.now().toString(), file: payload.file}
        );
        return result;
    },
}