

export interface IUserState {
    id: number | string
    email: string
    username: string
    role: EnumRoles
}

export interface ITokens {
    accessToken: string
    refreshToken: string
}

export interface IInitialState {
    user: IUserState | null
    isLoading: boolean
    isLogin: boolean
    message?: string
}

export interface ILoginData {
    username: string
    password: string
}
export interface IRegistrationData {
    username: string
    email: string
    password: string
}

export interface IAuthResponse extends ITokens{
    user: IUserState,
}

export const enum EnumSaveData {
    refresh = 'refreshToken',
    access = 'accessToken',
    user = 'user'
}

export const enum EnumRoles {
    USER = "USER",
    ADMIN = "ADMIN"
}

export interface IResponse {
    data:{
        message: string
    }
}