import React, {FunctionComponent} from 'react'
import { UserDesignProps} from './props'
import styles from "./UserDesign.module.css";

export const UserDesign: FunctionComponent<UserDesignProps> = ({
    imgUrl,
    text
}) => {

    return (
        <div className={styles.container} onClick={() => {window.location.href = `/overview?username=${text}`}}>
            <div className={styles.profileImage}>
                <img src={imgUrl} alt=""/>
            </div>
            <div className={styles.username}>
                <p>{text}</p>
            </div>
        </div>
    )
}