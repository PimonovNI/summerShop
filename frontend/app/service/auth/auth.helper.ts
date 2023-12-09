import Cookies from 'js-cookie';
import {EnumSaveData, IAuthResponse, ITokens} from "@/app/types/user.interface";
export const saveTokenStorage = (data: ITokens) => {
  Cookies.set(EnumSaveData.access, data.accessToken)
  Cookies.set(EnumSaveData.refresh, data.refreshToken, { expires: 30 });
}

export const removeFromStorage = () => {
  Cookies.remove(EnumSaveData.access)
  Cookies.remove(EnumSaveData.refresh)
  Cookies.remove(EnumSaveData.user)
  localStorage.removeItem(EnumSaveData.user)
}

export const saveToStorage = (data: IAuthResponse ) => {
  saveTokenStorage(data);
  Cookies.set(EnumSaveData.user, JSON.stringify(data.user))
  localStorage.setItem(EnumSaveData.user, JSON.stringify(data.user))
}

export const getAccessToken = () => {
  const accessToken = Cookies.get(EnumSaveData.access)
  return accessToken || null;
}