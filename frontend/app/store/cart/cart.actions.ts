import {createAsyncThunk} from "@reduxjs/toolkit";
import {CartService} from "@/app/service/cart.service";
import {ICartAdd} from "@/app/types/product.interface";

export const getCartProducts = createAsyncThunk(
	'cart/getCart',
	async (_, thunkApi) => {
		try {
			const response = await CartService.getCartProducts();
			return response.data;
		} catch (e) {
			return thunkApi.rejectWithValue(e)
		}
	}
)
export const addToCart = createAsyncThunk<string, ICartAdd>(
	'cart/addToCart',
	async (data, thunkApi) => {
		try {
			const response = await CartService.addToCart(data.request);
			return response.data;
		} catch (e) {
			return thunkApi.rejectWithValue(e);
		}
	}
);

export const deleteFromCart = createAsyncThunk<string, number>(
	'cart/deleteFromCart',
	async (id, thunkApi) => {
		try {
			const response = await CartService.deleteFromCart(id);
			return response.data;
		} catch (e) {
			return thunkApi.rejectWithValue(e)
		}
	}
)
export const sendPayment = createAsyncThunk(
	'cart/sendPayment',
	async (_, thunkApi) => {
		try {
			return await CartService.sendPayment()
		} catch (e) {
			return thunkApi.rejectWithValue(e)
		}
	}
)

