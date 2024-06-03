import {combineReducers} from "redux";
import userDetailsReducer from "./userDetails/user-details-reducer";
import userSearchReducer from "./userSearch/user-search-reducer";
import userLoginReducer from "./userLogin/user-login-reducer";
import studiesSaveReducer from "././././studies/studies-save-reducer";
import userRegisterReducer from "./userRegister/user-register-reducer";
import studiesUpdateReducer from "././studies/studies-update-reducer";
import studiesDeleteReducer from "././studies/studies-delete-reducer";
import experienceSaveReducer from "./experience/experience-save-reducer";
import experienceUpdateReducer from "./experience/experience-update-reducer";
import experienceDeleteReducer from "./experience/experience-delete-reducer";
import projectsSaveReducer from "./projects/projects-save-reducer";
import projectsUpdateReducer from "./projects/projects-update-reducer";
import projectsDeleteReducer from "./projects/projects-delete-reducer";


const layoutsReducer = combineReducers({
    userLogin: userLoginReducer,
    userDetails: userDetailsReducer,
    userSearch: userSearchReducer,
    studiesSave: studiesSaveReducer,
    userRegister: userRegisterReducer,
    studiesUpdate: studiesUpdateReducer,
    studiesDelete: studiesDeleteReducer,
    experienceSave: experienceSaveReducer,
    experienceUpdate: experienceUpdateReducer,
    experienceDelete: experienceDeleteReducer,
    projectSave: projectsSaveReducer,
    projectUpdate: projectsUpdateReducer,
    projectDelete: projectsDeleteReducer,
})

export default layoutsReducer;