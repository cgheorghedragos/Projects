import axios from "axios"

async function request(requestConfig) {
    let token = localStorage.getItem("auth");

    let headers = {
        'Authorization': `Bearer ${token}`
    }

    if (!(requestConfig.data instanceof FormData)) {
        headers["Content-Type"] = "application/json";
    }

    const response = await axios({
        method: requestConfig.method,
        url: requestConfig.url,
        headers: headers,
        data: requestConfig.data,
        params: requestConfig.params
    });

    return response.data;
}

async function getRequest(url, config = {}) {
    return request({
        ...config,
        url,
        method: "GET"
    });
}

async function postRequest(url, config = {}) {
    return request({
        ...config,
        url,
        method: "POST"
    });
}

async function putRequest(url, config = {}) {
    return request({
        ...config,
        url,
        method: "PUT"
    });
}

async function deleteRequest(url, config = {}) {
    return request({
        ...config,
        url,
        method: "DELETE"
    });
}



export {getRequest, postRequest, putRequest, deleteRequest}