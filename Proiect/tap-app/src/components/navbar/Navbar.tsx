import React, {FunctionComponent, useState} from 'react'
import {NavbarProps} from './props'
import styles from "./Navbar.module.css";
import {useDispatch} from "react-redux";

export const Navbar: FunctionComponent<NavbarProps> = ({
                                                           dispatchSuccessLogout,
                                                           userLogin
}) => {
    const dispatch = useDispatch();
    const [inputValue, setInputValue] = useState(''); // State to hold the input value

    return (
        <div className={styles.mainClass}>
            <div style={{flex: 1}}>
                <input
                    className={styles["search-bar"]}
                    type="text"
                    onChange={event => setInputValue(event.target.value)}
                    onKeyDown={(event) => {
                        if (event.key === "Enter"){
                            window.location.href=`/search?userSearched=${inputValue}`
                    }}}
                    placeholder="Search..."
                    style={{}}
                />
            </div>

            <div className={styles["nav-items"]}>
                <a href="/">Home</a>
                <a href="/login" onClick={() => dispatch(dispatchSuccessLogout())}>Logout</a>
            </div>
        </div>
    )
}