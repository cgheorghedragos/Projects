import React, {FunctionComponent, useEffect, useState} from 'react';
import {LoginPageProps} from './props';
import styles from "./LoginPage.module.css";
import {UserDetailsPayload} from "../../packages/types/layouts/UserDetailsLayout";

export const LoginPage: FunctionComponent<LoginPageProps> = ({
                                                                 userLoginState,
                                                                 dispatchUserLogin
                                                             }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (username.trim().length < 4 || password.trim().length < 4) {
            setError('Username and password must be at least 4 characters long.');
            return;
        }
        const userCredentials: UserDetailsPayload = {username: username, password: password};
        dispatchUserLogin(userCredentials);
    };

    const handleInputChange = (setter: React.Dispatch<React.SetStateAction<string>>, value: string) => {
        setter(value);
        if (value.trim().length < 4) {
            setError('Username and password must be at least 4 characters long.');
        } else {
            setError('');
        }
    };

    useEffect(() => {
        console.log(userLoginState.userLogin.access_token)
        if (userLoginState.userLogin.access_token.length > 0) {
            window.location.href = '/'
        }
        if (userLoginState.error) {
            setError(userLoginState.error)
        }
    }, [userLoginState]);

    const handleRegister = () => {
        window.location.href = '/register'
    };

    return (
        <div className={styles.mainClass}>
            <form onSubmit={handleLogin} className={styles["login-form"]}>
                <h2>Login</h2>
                {error && <p className={styles.error}>{error}</p>}
                <div className={styles["input-group"]}>
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={e => handleInputChange(setUsername, e.target.value)}
                        required
                    />
                </div>
                <div className={styles["input-group"]}>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={e => handleInputChange(setPassword, e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className={styles["login-button"]}>Login</button>
                <button type="button" className={styles["register-button"]} onClick={handleRegister}>Go to Register
                </button>
            </form>
        </div>
    );
}
