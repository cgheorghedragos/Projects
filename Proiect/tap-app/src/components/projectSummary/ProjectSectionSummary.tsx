import React, {FunctionComponent} from 'react'
import { ProjectSectionSummaryProps } from './props'
import styles from "./ProjectSectionSummary.module.css";

export const ProjectSectionSummary: FunctionComponent<ProjectSectionSummaryProps> = ({
    imgUrl,
    text
}) => {

    return (
        <div className={styles.container}>
            <div className={styles["image-container"]}>
                <img src={imgUrl} alt=""/>
            </div>
            <div className={styles["text-container"]}>
                <p>{text}</p>
            </div>
        </div>
    )
}