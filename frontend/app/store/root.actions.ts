import * as userActions from './user/user.actions'
import * as cartActions from './cart/cart.actions'
import {filterSlice} from "@/app/store/filter/filter.slice";
import {cartSlice} from "@/app/store/cart/cart.slice";
export const rootActions = {
	...userActions,
	...cartActions,
	...filterSlice.actions,
	...cartSlice.actions,
}