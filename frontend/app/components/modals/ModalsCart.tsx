import React, {useEffect} from 'react';
import {RxCross1} from "react-icons/rx";
import CartProductsItem from "@/app/components/products/CartProductsItem";
import {useCart} from "@/app/hooks/useCart";
import {useActions} from "@/app/hooks/useActions";
import {toPrice} from "@/app/utils/toPrice";
import {usePathname, useRouter} from "next/navigation";
import CartProductList from "@/app/components/products/CartProductList";
interface IProps {
	handleClose: () => void
}

const ModalsCart = ({handleClose}: IProps) => {
	const cart = useCart()
	const actions = useActions()
	const router = useRouter()
	useEffect(() => {
		actions.getCartProducts()
	}, []);
	const handlePayment = () => {
			if (cart.products.length > 0){
				router.push("/cart/payment")
				handleClose()
			}

	}
	return (
		<div className="fixed inset-0 flex items-center justify-center z-10 overflow-y-scroll"
				 onClick={()=> handleClose()}>
			<div className="flex flex-col bg-bgColor rounded-[7px] w-2/3 h-[600px] "
					 onClick={(event)=> {
				event.stopPropagation()}}>
				<div className="p-2 border border-b-secondary rounded-t-[7px] relative">
					<h2 className="text-xl font-semibold">Кошик</h2>
					<RxCross1 className="absolute right-1 top-1 cursor-pointer"
										onClick={()=> handleClose()}/>
				</div>
				<div className="flex-auto overflow-y-auto">
					<CartProductList cart={cart.products}/>
				</div>
				<div className="flex items-center justify-end mr-[7%] my-3">
					<div className="bg-primary bg-opacity-20 p-5 rounded-[7px] border border-primary">
						<span>Загальна сума: <span className="text-xl font-medium">{toPrice(cart.total)}</span></span>
						<button className="w-[200px] text-[18px] rounded-[7px] bg-primary py-2 ml-4" onClick={handlePayment}>Замовити</button>
					</div>
				</div>
			</div>
		</div>
	);
};

export default ModalsCart;