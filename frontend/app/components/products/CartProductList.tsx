import React from 'react';
import CartProductsItem from "@/app/components/products/CartProductsItem";
import {IProductCartItem} from "@/app/types/product.interface";

interface IProps {
	cart: IProductCartItem[]
}
const CartProductList = ({cart}:IProps) => {
	return (
		<div>
			{cart.length > 0 ?
				cart.map((product) => <CartProductsItem key={product.id} product={product}/>)
				: <div className="flex justify-center">Кошик порожній</div>
			}
		</div>
	);
};

export default CartProductList;