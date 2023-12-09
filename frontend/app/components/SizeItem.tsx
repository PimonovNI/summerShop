import {IProductProperty} from "@/app/types/product.interface";
import React from "react";

interface IProps {
	size: IProductProperty,
	count: number,
	changeSizeCount: (size: IProductProperty, newCount: number) => void
}
const SizeItem = ({ size, changeSizeCount, count }: IProps) => {
	return (
		<div>
			<button className=" w-[20px] text-[18px] rounded-l bg-secondary py-1"
							onClick={() => changeSizeCount(size, count-1)}>-</button>
			<input min="0"
						 inputMode="numeric"
						 pattern="[0-9]*"
						 type="number"
						 value={count}
						 className="appearance-none max-w-[50px] text-center custom-input py-1"
						 onChange={(e) => changeSizeCount(size, parseInt(e.target.value))}/>
			<button className=" w-[20px] text-[18px] rounded-r bg-secondary py-1"
							onClick={() => changeSizeCount(size, count+1)}>+</button>
		</div>
	);
}

export default SizeItem