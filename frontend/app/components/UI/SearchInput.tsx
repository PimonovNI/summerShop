import React, { ChangeEvent, useState, useEffect } from 'react';
import { FaMagnifyingGlass } from "react-icons/fa6";
import { useActions } from "@/app/hooks/useActions";
import { EnumParams } from "@/app/types/main.interface";
import { useFilter } from "@/app/hooks/useFilter";

const SearchInput: React.FC = () => {
	const action = useActions();
	const filterList = useFilter();

	// const debounce = (fn: Function, delay: number) => {
	// 	let timer: NodeJS.Timeout | null = null;
	// 	return (...args: any) => {
	// 		if (timer) {
	// 			clearTimeout(timer);
	// 		}
	// 		timer = setTimeout(() => {
	// 			console.log(...args)
	// 			fn(...args);
	// 		}, delay);
	// 	};
	// };
	//
	// const debouncedUpdateFilter = debounce(action.updateFilter, 300);

	// useEffect(() => {
	// 	debouncedUpdateFilter({ key: EnumParams.search, item: filterList.search });
	// }, [filterList.search]);

	const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
		action.updateFilter({key: EnumParams.search, item: e.target.value});
	};

	return (
		<div className="flex bg-bgColor max-w-[400px] rounded-full h-8 px-5 items-center justify-center">
			<input
				placeholder="Запит..."
				value={filterList.search}
				onChange={handleInputChange}
				className="bg-transparent border-none h-full text-[1rem] w-full focus:outline-none"
			/>
			<FaMagnifyingGlass className="text-secondary" />
		</div>
	);
};

export default SearchInput;
