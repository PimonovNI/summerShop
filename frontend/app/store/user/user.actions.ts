import {createAsyncThunk} from "@reduxjs/toolkit";
import {IAuthResponse, ILoginData, IRegistrationData, IResponse} from "@/app/types/user.interface";
import {AuthService} from "@/app/service/auth/auth.service";
import {removeFromStorage} from "@/app/service/auth/auth.helper";

export const registration = createAsyncThunk<IResponse, IRegistrationData>(
	'auth/registration',
	async (data, thunkApi) =>{
		try {
			return await AuthService.Registration(data)
		} catch (e) {
			return thunkApi.rejectWithValue(e)
		}
	}
)

export const login = createAsyncThunk<IAuthResponse, ILoginData>(
	'auth/login',
	async (data, thunkApi) =>{
		try {
			return await AuthService.Login(data)
		} catch (e) {
			return thunkApi.rejectWithValue(e)
		}
	}
)

export const logout = createAsyncThunk('auth/logout', () => {removeFromStorage()})

export const checkAuth = createAsyncThunk(
	'auth/checkAuth',
	async (_, thunkApi) => {
		try {
			return await AuthService.getNewTokens()
		} catch (e) {
			thunkApi.dispatch(logout())

			return thunkApi.rejectWithValue(e)
		}
	}
)