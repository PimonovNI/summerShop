import instance from "@/app/api/api.interseptor";
import {ICartAdd, IProductCartItem, IProductResponse} from "@/app/types/product.interface";

export const CartService = {
	async getCartProducts(){
		return instance<IProductCartItem[]>({
			url: `${process.env.NEXT_PUBLIC_CART}`,
			method: 'GET',
		})
	},
	async addToCart(data: any){
		return instance<string>({
			url: `${process.env.NEXT_PUBLIC_CART}`,
			data,
			method: 'POST',
		})
	},
	async deleteFromCart(id: number){
		return instance<string>({
			url: `${process.env.NEXT_PUBLIC_CART}/${id}`,
			method: 'DELETE',
		})
	},
	async sendPayment(){
		return instance<string>({
			url: `${process.env.NEXT_PUBLIC_CART_PAYMENT}`,
			method: 'POST',
		})
	},
}