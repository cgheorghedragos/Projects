const API_BASE = "/rest"
const PORT = "8083";
const DOMAIN = "http://localhost"
const API_DOMAIN = `${DOMAIN}:${PORT}`
const API = {
    LOGIN: `${API_DOMAIN}/api/login`,
    OVERVIEW: `${API_DOMAIN}${API_BASE}/overview`,
    EXPERIENCE_SAVE: `${API_DOMAIN}${API_BASE}/experience/save`,
    EXPERIENCE_UPDATE: `${API_DOMAIN}${API_BASE}/experience/update`,
    EXPERIENCE_DELETE: `${API_DOMAIN}${API_BASE}/experience/delete`,
    STUDIES_SAVE: `${API_DOMAIN}${API_BASE}/studies/save`,
    STUDIES_UPDATE: `${API_DOMAIN}${API_BASE}/studies/update`,
    STUDIES_DELETE: `${API_DOMAIN}${API_BASE}/studies/delete`,
    PROJECT_SAVE: `${API_DOMAIN}${API_BASE}/project/save`,
    PROJECT_UPDATE: `${API_DOMAIN}${API_BASE}/project/update`,
    PROJECT_DELETE: `${API_DOMAIN}${API_BASE}/project/delete`,
    FIND_USER: `${API_DOMAIN}${API_BASE}/find-by-username`,
    FIND_USERS: `${API_DOMAIN}${API_BASE}/find-users-by-username`,
    UPLOAD_PHOTO: `${API_DOMAIN}${API_BASE}/upload`,
    REGISTER_USER: `${API_DOMAIN}${API_BASE}/register-user`,
    SEARCH_USER_DETAILS: `${API_DOMAIN}${API_BASE}/search-users`,
};

export { API_BASE, API, API_DOMAIN }