import React, {FunctionComponent, useEffect} from 'react'
import {SearchPageProps} from './props'
import styles from "./SearchPage.module.css";
import Navbar from "../../components/navbar";
import {useDispatch} from "react-redux";
import UserDesign from "../../components/userdesign";

export const SearchPage: FunctionComponent<SearchPageProps> = ({
                                                                   users,
                                                                   dispatchSearchUsers
                                                               }) => {
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(dispatchSearchUsers( new URLSearchParams(window.location.search).get('userSearched') || ''));
    }, []);
    const usersArray = Object.values(users);


    return (
        <div className={styles.mainClass}>
            <Navbar/>
            <div className={styles.container}>
                {usersArray?.map(user =>
                    <div className={styles.items}>
                        <UserDesign imgUrl={user.photoUrl!} text={user.username!}/>
                    </div>
                )}
            </div>
        </div>
    )
}