export type PhotoDTO = {
    id?: number;
    photoUrl?: string;
    file?: string;
}

export type UploadPhotoDTO = {
    photo_url: string;
}

export type UploadPhotoReq = {
    file: File
}