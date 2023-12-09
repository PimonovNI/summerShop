import { createSlice } from '@reduxjs/toolkit';
import {IProductCart} from "@/app/types/product.interface";
import {addToCart, deleteFromCart, getCartProducts, sendPayment} from "@/app/store/cart/cart.actions";

const initialState : IProductCart = {
	isLoading: false,
	products: [],
	total: 0,
	quantity: 0
}
export const cartSlice = createSlice({
	name: 'cart',
	initialState,
	reducers: {
		setTotalAndQuantity: (state) => {
			state.total = state.products.reduce((accumulator, product) => {
				return accumulator + product.price * product.count;
			}, 0);
			state.quantity = state.products.length
		},
		setItemCount: (state, action) => {
			state.products = state.products.map((product) =>
				product.id === action.payload.id ? { ...product, count: Math.max(1, action.payload.count) } : product
			)
			cartSlice.caseReducers.setTotalAndQuantity(state)
		},
		setItemSize: (state, action) => {
			state.products = state.products.map((product) =>
				product.id === action.payload.id ? { ...product, size: action.payload.name} : product
			)
		},
	},
	extraReducers: (builder) => {
		builder
			.addCase(getCartProducts.pending, (state) => {
				state.isLoading = true;
			})
			.addCase(getCartProducts.fulfilled, (state, action) => {
				state.isLoading = false;
				state.products = action.payload;
				cartSlice.caseReducers.setTotalAndQuantity(state);
			})
			.addCase(getCartProducts.rejected, (state) => {
				state.isLoading = false;
				state.products = [];
			})
			.addCase(addToCart.fulfilled, (state, action) => {
				const newItem = action.meta.arg;
				cartSlice.caseReducers.setTotalAndQuantity(state);
				state.products.push(newItem.product);
			})
			.addCase(deleteFromCart.fulfilled, (state, action) => {
				const itemId = action.meta.arg;
				state.products = state.products.filter((product) => product.id !== itemId);
				cartSlice.caseReducers.setTotalAndQuantity(state);
			})
			.addCase(sendPayment.fulfilled, (state) => {
				state.products = [];
				state.total = 0;
				state.quantity = 0;
			});
	}
});
export const { setTotalAndQuantity, setItemCount } = cartSlice.actions;
