import React, {ChangeEvent, useEffect, useState} from 'react';
import {useFilter} from "@/app/hooks/useFilter";
import {useActions} from "@/app/hooks/useActions";
import {useMinMaxPrice} from "@/app/hooks/useMinMaxPrice";
import {EnumParams} from "@/app/types/main.interface";
const PriceFilter = () => {
	const filter = useFilter()
	const actions = useActions()

	const [value, setValue] = useState({
		minValue: filter.price.minValue,
		maxValue: filter.price.maxValue,
	})
	useEffect(() => {
		setValue({
			minValue: filter.price.minValue,
			maxValue: filter.price.maxValue,
		})
	}, [filter.price]);
	const handleMinValue = (e: ChangeEvent<HTMLInputElement>) =>{
		if(+e.target.value < filter.minMaxPrice.minValue) setValue({
			maxValue: value.maxValue,
			minValue: filter.minMaxPrice.minValue,
		})
		else setValue({
			minValue: +e.target.value >= value.maxValue ? value.maxValue - 1 : +e.target.value,
			maxValue: value.maxValue,
		});
	}
	const handleMaxValue = (e: ChangeEvent<HTMLInputElement>) =>{
		if(+e.target.value > filter.minMaxPrice.maxValue) setValue({
			maxValue: filter.minMaxPrice.maxValue,
			minValue: value.minValue,
		})
		else setValue({
				maxValue: +e.target.value <= value.minValue ? value.minValue + 1 : +e.target.value,
				minValue: value.minValue,
		})}
	return (
		<div>
			<div  className=" justify-center flex">
				<div>
					<input type="number"
								 className="w-[65px] sm:mb-1 sm:mr-1 2xl:mb-0 2xl:mr-0 p-1 custom-input focus:outline-none border rounded-[7px] text-center"
								 value={value.minValue}
								 onChange={handleMinValue}/>
					<span className="p-2 hidden 2xl:inline">-</span>
					<input type="number"
								 className="w-[65px] p-1 custom-input focus:outline-none border rounded-[7px] text-center"
								 value={value.maxValue}
								 onChange={handleMaxValue}/>
				</div>
				<button className="mx-2 bg-primary px-2 py-1 rounded-[7px]"
								onClick={()=>actions.updateFilter({key: EnumParams.price , item: value})}>OK</button>
			</div>
			<div className="mt-3 mr-2">
				<div className="relative bg-bgColor h-[5px] rounded-[5px]">
					<div className="absolute h-[5px] rounded-[5px] bg-primary" style={{
						left: `${Math.round((value.minValue / filter.minMaxPrice.maxValue) * 100)}%`,
						right: `${100 - Math.round((value.maxValue / filter.minMaxPrice.maxValue) * 100)}%`}}></div>
				</div>
				<div className="relative range-input">
					<input
						type="range"
						min={filter.minMaxPrice.minValue}
						max={filter.minMaxPrice.maxValue}
						value={value.minValue >= value.maxValue ? value.maxValue - 1 : value.minValue }
						onChange={handleMinValue}
						step={1}
					/>
					<input
						type="range"
						min={filter.minMaxPrice.minValue}
						max={filter.minMaxPrice.maxValue}
						value={value.maxValue <= value.minValue ? value.minValue + 1 : value.maxValue }
						onChange={handleMaxValue}
						step={1}
					/>
				</div>
			</div>
		</div>
	);
};

export default PriceFilter;