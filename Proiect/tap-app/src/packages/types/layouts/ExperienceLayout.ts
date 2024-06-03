import {UserDetailResponse} from "./UserDetailsLayout";
import {PhotoDTO} from "./PhotosLayout";

export type ExperienceDTO = {
    id?: number | null;
    isPersonalProject?: boolean;
    startDate?: Date | null;
    endDate?: Date | null;
    description?: string;
    user?: UserDetailResponse | null;
    photos?: PhotoDTO[] | null;
    location?: string;
}