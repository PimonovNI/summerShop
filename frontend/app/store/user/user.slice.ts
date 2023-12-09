import { IInitialState} from "@/app/types/user.interface";
import {createSlice} from "@reduxjs/toolkit";
import {checkAuth, login, logout, registration} from "@/app/store/user/user.actions";
import {errorCatch} from "@/app/api/api.helper";

const initialState: IInitialState = {
	user : null,
	isLoading: false,
	isLogin: false,
	message: ""
}

export const userSlice = createSlice({
	name: 'user',
	initialState,
	reducers: {},
	extraReducers: (builder) => {
		builder.addCase(registration.pending, (state) => {
			state.isLoading = true
		})
		.addCase(registration.fulfilled, (state, action) => {
			state.message = errorCatch(action.payload) || "success"
			state.isLoading = false
		})
		.addCase(registration.rejected, (state) => {
			state.isLoading = false
			state.user = null
		})

			.addCase(login.pending, (state) => {
				state.isLoading = true
			})
			.addCase(login.fulfilled, (state, action) => {
				state.isLoading = false
				state.user = action.payload.user
				state.isLogin = true
				state.message = errorCatch(action.payload) || "success"
			})
			.addCase(login.rejected, (state) => {
				state.isLoading = false
				state.user = null
			})

			.addCase(logout.fulfilled, (state) => {
				state.isLoading = false
				state.user = null
				state.isLogin = false
			})

			.addCase(checkAuth.fulfilled, (state, action) => {
				state.user = action.payload.user
				state.isLogin = true
			})
			.addCase(checkAuth.rejected, (state) => {
				state.isLogin = false
			})
	}
})