import React from 'react';

interface IProps {
	changeCount: (num: number) => void,
	count: number,
}
const ProductCount = ({changeCount, count}: IProps) => {
	return (
		<div>
			<button className="w-[20px] text-[18px] rounded-l bg-secondary py-1"
							onClick={() => changeCount(count-1)}>-</button>
			<input min="0"
						 inputMode="numeric"
						 pattern="[0-9]*"
						 type="number"
						 value={count}
						 className="appearance-none max-w-[50px] text-center custom-input py-1"
						 onChange={(e) => changeCount(+e.target.value)}/>
			<button className=" w-[20px] text-[18px] rounded-r bg-secondary py-1"
							onClick={() => changeCount(count+1)}>+</button>
		</div>
	);
};

export default ProductCount;