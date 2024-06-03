import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import {ROUTES} from '../../config/routes'

// Lazy load your components
const OverviewPage = React.lazy(() => import('../overview'));
const DetailsPage = React.lazy(() => import('../details'));
const LoginPage = React.lazy(() => import('../login'));
const RegisterPage = React.lazy(() => import('../register'));
const SearchPage = React.lazy(() => import('../searchUser'));

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={ROUTES.MAIN} element={<OverviewPage/>}/>
                <Route path={ROUTES.OVERVIEW} element={<OverviewPage/>}/>
                <Route path={ROUTES.DETAILS} element={<DetailsPage/>}/>
                <Route path={ROUTES.LOGIN} element={<LoginPage/>}/>
                <Route path={ROUTES.REGISTER} element={<RegisterPage/>}/>
                <Route path={ROUTES.SEARCH} element={<SearchPage/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
