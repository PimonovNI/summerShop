import React, {useState} from 'react';
import Image from "next/image";
import {toPrice} from "@/app/utils/toPrice";
import ProductCount from "@/app/components/UI/ProductCount";
import {IProductCartItem} from "@/app/types/product.interface";
import {LuTrash} from "react-icons/lu";
import {useActions} from "@/app/hooks/useActions";
import Select from "@/app/components/UI/Select";
import {useSizes} from "@/app/hooks/productHooks/useSizes";
import {EnumParams} from "@/app/types/main.interface";
import {dropDownFilter} from "@/app/constants/main.constants";
import {useCart} from "@/app/hooks/useCart";
import {findId} from "@/app/utils/findId";

interface IProps {
	product: IProductCartItem,
}
const CartProductsItem = ({product}: IProps) => {
	const cart = useCart()
	const sizes = useSizes()
	const actions = useActions()
	const handleUpdateCount = (count: number) => {
		// actions.addToCart({
		// 	product: {...product, count},
		// 	request: {
		// 		product: product.id,
		// 		count,
		// 		size: findId(product.size, sizes?.data || [])?.id || 1
		// 	}
		// })
		actions.setItemCount({id: product.id, count})
	}
	const handleUpdateSize = (size: string) => {
		// actions.addToCart({
		// 	product: {...product, size},
		// 	request: {
		// 		product: product.id,
		// 		count: product.count,
		// 		size: findId(size, sizes?.data || [])?.id || 1
		// 	}
		// })
		actions.setItemSize({id: product.id, size})
	}
	return (
		<div className="flex m-1">
			<Image src={product.photo}
						 className="rounded-[7px] mr-2"
						 alt={product.name}
						 priority
						 width={100}
						 height={100}/>
			<div className="flex justify-between items-center w-full mr-5">
				<div className="flex flex-col flex-2 justify-center">
					<span className="text-2xl mt-1">{product.name}</span>
					<label className="mt-1">Розмір:
						<select className="outline-none bg-transparent font-medium text-center cursor-pointer"
						onChange={(event)=> handleUpdateSize(event.target.value)}
										value={product.size}>
						{sizes?.data?.map(option =>
							<option key={option.id}
											value={option.name}>
								{option.name}
							</option>)}
					</select></label>
				</div>
				<span className="mt-1 flex-1 text-xl font-medium">{toPrice(product.price)}</span>
				<div className="flex mt-1 flex-1 items-center">
					<span className="mr-1">Кількість:</span>
					<ProductCount count={product.count} changeCount={(count) => handleUpdateCount(count)}/>
				</div>
				<LuTrash onClick={()=> actions.deleteFromCart(product.id)} className="text-primary text-xl cursor-pointer"/>
			</div>

		</div>
	);
};

export default CartProductsItem;