import axios from "axios";
import { getContentType} from "@/app/api/api.helper";
import {getAccessToken} from "@/app/service/auth/auth.helper";

const instance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_SERVER_URL,
    headers: getContentType()
})

instance.interceptors.request.use(async config => {
    const accessToken = getAccessToken();

    if(config && config.headers && accessToken)
        config.headers.Authorization = `Bearer ${accessToken}`;

    return config;
})


export default instance;