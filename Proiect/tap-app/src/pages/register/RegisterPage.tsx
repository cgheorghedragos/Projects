import React, {FunctionComponent, useEffect, useState} from 'react';
import {RegisterPageProps} from './props';
import styles from "./RegisterPage.module.css";
import {useDispatch} from "react-redux";
import {UserDetailsPayload} from "../../packages/types/layouts/UserDetailsLayout";

export const RegisterPage: FunctionComponent<RegisterPageProps> = ({
                                                                       handleLogin,
                                                                       userRegisteredState,
                                                                       dispatchUserRegister
                                                                   }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errors, setErrors] = useState<{ username?: string; password?: string; confirmPassword?: string }>({});
    const dispatch = useDispatch();
    const handleRegister = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if (validateForm()) {
            return;
        }
        setIsSubmitting(true);
        const userCredentials: UserDetailsPayload = {
            username: username,
            password: password
        }
        dispatch(dispatchUserRegister(userCredentials));
    };

    useEffect(() => {
        if (isSubmitting) {
            setTimeout(() => setIsSubmitting(false), 1000);
        }
    }, [isSubmitting]);

    useEffect(() => {
        if (userRegisteredState && userRegisteredState.userRegister && userRegisteredState.userRegister.username) {
            window.location.href = '/login'
        }
        if (userRegisteredState && userRegisteredState.error) {
            setErrors({confirmPassword: userRegisteredState.error})
        }
    }, [userRegisteredState]);

    const validateForm = () => {
        const newErrors: { username?: string; password?: string; confirmPassword?: string } = {};

        if (username.trim().length < 4) {
            newErrors.username = "Username must be at least 4 characters long.";
        }

        if (password.trim().length < 4) {
            newErrors.password = "Password must be at least 4 characters long.";
        }

        if (password !== confirmPassword) {
            newErrors.confirmPassword = "Passwords do not match.";
        }

        if (Object.keys(newErrors).length > 0) {
            setErrors(newErrors);
        } else {
            setErrors({});
        }
        return Object.keys(newErrors).length > 0;
    }

    return (
        <div className={styles.mainClass}>
            <form onSubmit={handleRegister} className={styles["login-form"]}>
                <h2>Register</h2>
                <div className={styles["input-group"]}>
                    <label htmlFor="username">Username:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                        required
                    />
                    {errors.username && <p className={styles.error}>{errors.username}</p>}
                </div>
                <div className={styles["input-group"]}>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className={styles["input-group"]}>
                    <label htmlFor="confirmPassword">Confirm password:</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={e => setConfirmPassword(e.target.value)}
                        required
                    />
                    {errors.confirmPassword && <p className={styles.error}>{errors.confirmPassword}</p>}
                </div>
                <button type="submit" className={styles["register-button"]}>Sign up</button>
                <button type="button" className={styles["login-button"]} onClick={handleLogin}>Go to Login</button>
            </form>
        </div>
    );
};
