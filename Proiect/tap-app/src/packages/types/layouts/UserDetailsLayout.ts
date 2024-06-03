import {ExperienceDTO} from "./ExperienceLayout";

export type UserDetailResponse = {
    id: number | null;
    username: string | null;
    photoUrl: string | null;
    friends: SimpleUserDTO[] | null;
    pendingFriends: SimpleUserDTO[] | null;
    experiences: ExperienceDTO[] | null;
    studies: ExperienceDTO[] | null;
    isPersonalAccount: boolean;
}

export type UserDetailsPayload = {
    username: string,
    password: string
}

export type UserLoginResponse = {
    access_token: string,
    refresh_token: string
}

export type SimpleUserDTO = {
    id: number | null;
    username: string | null;
    photoUrl: string | null;
}

export type LoginLayout = {
    error: string | null;
    userLogin: UserLoginResponse;
}

export type ErrorType = {
    error: string
}

export type UserRegisterLayout = {
    error: string | null;
    userRegister: UserDetailResponse;
}