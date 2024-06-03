import React, {FunctionComponent, useState} from 'react'
import {ExperienceProps} from './props'
import styles from "./Experience.module.css";
import DialogForm from "../dialog";
import {useDispatch} from "react-redux";

export const Experience: FunctionComponent<ExperienceProps> = ({
                                                                   id,
                                                                   title,
                                                                   startDate,
                                                                   endDate,
                                                                   description,
                                                                   onDeleteDispatch,
                                                                   onUpdateDispatch,
                                                                   isPersonalAccount
                                                               }) => {
    const [startDateState, setStartDateState] = useState(new Date(startDate))
    const [endDateState, setEndDateState] = useState(new Date(endDate))
    const [showUpdateDialog, setShowUpdateDialog] = useState(false)
    const [showDeleteDialog, setShowDeleteDialog] = useState(false)
    const dispatch = useDispatch();
    return (
        <div className={styles.mainClass}>
            <h2>{title}</h2>
            <h3>{startDateState.toLocaleDateString() + " - " + endDateState.toLocaleDateString()}</h3>
            <p>{description}</p>
            <div className={styles["button-container"]}>
                <button hidden={!isPersonalAccount} onClick={() => {
                    setShowUpdateDialog(true)
                }} className={styles["edit-button"]}>Edit
                </button>
                <button hidden={!isPersonalAccount} onClick={() => {
                    dispatch(onDeleteDispatch(id || 0))
                }} className={styles["delete-button"]}>Delete
                </button>
            </div>

            {showUpdateDialog && (
                <DialogForm onClose={() => {
                    setShowUpdateDialog(false)
                }}
                            id={id}
                            onDispatch={onUpdateDispatch}
                            isPersonalProject={false}
                            locationName={title}
                            startDate={startDateState}
                            endDate={endDateState}
                            description={description}
                            title={`Update ${title}`}/>
            )}
        </div>
    )
}