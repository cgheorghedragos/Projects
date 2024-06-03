import React, {FunctionComponent, useEffect} from 'react'
import {OverviewPageProps} from './props'
import Navbar from "../../components/navbar/index";
import Section from "../../components/section";
import styles from "./OverviewPage.module.css";
import {useDispatch} from "react-redux";

export const OverviewPage: FunctionComponent<OverviewPageProps> = ({
                                                                       userDetails,
                                                                       changesDone,
                                                                       dispatchGetUserDetails,
                                                                   }) => {

    const dispatch = useDispatch();
    useEffect(() => {
        const searchParams = new URLSearchParams(window.location.search);
        const user = searchParams.get('username');

        if (userDetails.username == null || userDetails.username == '' || (user != null && user != userDetails.username)) {
            dispatch(dispatchGetUserDetails(user))
        }

    }, [userDetails, dispatchGetUserDetails])

    useEffect(() => {
        if (changesDone) {
            window.location.reload();
        }
    }, [changesDone]);

    return (
        <div className={styles.mainClass}>
            <Navbar/>
            <div className={styles.container}>
                <Section sectionTitle={userDetails.username}
                         isPersonalAccount={userDetails.isPersonalAccount}
                         studies={userDetails.studies}
                         experience={userDetails.experiences?.filter(experience => experience.isPersonalProject === false) || null}
                         projects={userDetails.experiences?.filter(experience => experience.isPersonalProject === true) || null}/>
            </div>
        </div>
    )
}