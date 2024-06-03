import {FunctionComponent, useState} from "react";
import {DialogFormProps} from "./props";
import styles from "./Dialog.module.css";
import {ExperienceDTO} from "../../packages/types/layouts/ExperienceLayout";
import {useDispatch} from "react-redux";
import {PhotoDTO} from "../../packages/types/layouts/PhotosLayout";
import {fileToBase64} from "../../packages/util/FileParser";

const DialogForm: FunctionComponent<DialogFormProps> = ({
                                                            title,
                                                            locationName,
                                                            startDate,
                                                            endDate,
                                                            description,
                                                            onClose,
                                                            onDispatch,
                                                            isPersonalProject,
                                                            id,
    formatDate
                                                        }) => {
    const dispatch = useDispatch();
    const [photo, setPhoto] = useState<PhotoDTO[] | null>(null);

    const handlePhotoChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const filesArray = Array.from(e.target.files);

            Promise.all(filesArray.map(async (file) => {
                const base64 = await fileToBase64(file);
                const photoDTO: PhotoDTO = {file: base64}
                return photoDTO;
            }))
                .then(filesData => {
                    setPhoto(filesData);
                })
                .catch();
        }
    };
    console.log("DATE:")
    console.log(startDate)
    const [formData, setFormData] = useState({
        location: locationName || '',
        startDate: startDate || null,
        endDate: endDate || null,
        description: description || ''
    });

    const [errors, setErrors] = useState({
        location: '',
        startDate: '',
        endDate: '',
        description: ''
    });

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target;
        console.log()
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const validateForm = () => {
        let valid = true;
        let errors = {
            location: '',
            startDate: '',
            endDate: '',
            description: ''
        };

        if (formData.location.trim().length < 4) {
            errors.location = 'Location should be at least 4 characters long.';
            valid = false;
        }

        if (formData.description.trim().length < 10) {
            errors.description = 'Description should be at least 10 characters long.';
            valid = false;
        }

        if (!formData.startDate) {
            errors.startDate = 'Start Date is required.';
            valid = false;
        }

        if (!formData.endDate) {
            errors.endDate = 'End Date is required.';
            valid = false;
        }

        if (formData.startDate && formData.endDate && new Date(formData.startDate) >= new Date(formData.endDate)) {
            errors.startDate = 'Start Date should be before End Date.';
            errors.endDate = 'End Date should be after Start Date.';
            valid = false;
        }

        setErrors(errors);
        return valid;
    };

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (!validateForm()) {
            return;
        }
        const experienceDTO: ExperienceDTO = {
            id: id,
            location: formData.location,
            isPersonalProject: isPersonalProject,
            description: formData.description,
            startDate: formData.startDate,
            endDate: formData.endDate,
            photos: photo
        }

        dispatch(onDispatch(experienceDTO));

        onClose();
    };

    return (
        <div className={styles.overlay}>
            <div className={styles.dialog}>
                {title && <h2>{title}</h2>}
                <form onSubmit={handleSubmit}>
                    <label>
                        Location:
                        <input
                            type="text"
                            name="location"
                            value={formData.location}
                            onChange={handleInputChange}
                        />
                        {errors.location && <span className={styles.error}>{errors.location}</span>}
                    </label>
                    <label>
                        Start Date:
                        <input
                            type="date"
                            name="startDate"
                            value={formData.startDate ? formatDate(formData.startDate) : ""}
                            onChange={handleInputChange}
                        />
                        {errors.startDate && <span className={styles.error}>{errors.startDate}</span>}
                    </label>
                    <label>
                        End Date:
                        <input
                            type="date"
                            name="endDate"
                            value={formData.endDate ? formatDate(formData.endDate) : ""}
                            onChange={handleInputChange}
                        />
                        {errors.endDate && <span className={styles.error}>{errors.endDate}</span>}

                    </label>
                    <label>
                        Description:
                        <textarea
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                        />
                        {errors.description && <span className={styles.error}>{errors.description}</span>}
                    </label>
                    {isPersonalProject && (<label>
                        Upload Photo:
                        <input
                            type="file"
                            name="photo"
                            onChange={handlePhotoChange}
                            multiple
                        />
                    </label>)}
                    <button type="submit">Submit</button>
                    <button type="button" onClick={onClose}>Close</button>
                </form>
            </div>
        </div>
    );
};
export default DialogForm;