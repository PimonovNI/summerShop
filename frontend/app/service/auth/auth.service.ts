import Cookies from "js-cookie";
import {EnumSaveData, IAuthResponse, ILoginData, IRegistrationData} from "@/app/types/user.interface";
import axios from "axios";
import {getContentType} from "@/app/api/api.helper";
import {saveToStorage} from "@/app/service/auth/auth.helper";
import instance from "@/app/api/api.interseptor";


export const AuthService = {
    async Registration(data: IRegistrationData){
        try {
            const response = await instance({
                url: process.env.NEXT_PUBLIC_REGISTRATION_URL,
                method: 'POST',
                data
            })
            return response.data
        } catch (e:any) {
            return e.response.data
        }

    },
    async Login(data: ILoginData){
        try {
            const response = await instance<IAuthResponse>({
                url: process.env.NEXT_PUBLIC_LOGIN_URL,
                method: 'POST',
                data
            })

            if (response.data.accessToken) saveToStorage(response.data)

            return response.data
        } catch (e:any) {
            return e.response.data
        }
    },

    async getNewTokens() {
        const refreshToken = Cookies.get(EnumSaveData.refresh);

        const response = await axios.post<string, { data: IAuthResponse }>(
          `${process.env.NEXT_PUBLIC_SERVER_URL}${process.env.NEXT_PUBLIC_NEW_TOKENS_URL}`,
          {refreshToken},
          {
              headers: {
                  ...getContentType(),
              }
          }
        );

        if (response.data.accessToken) {
            saveToStorage(response.data);
        }

        return response.data;
    }

}