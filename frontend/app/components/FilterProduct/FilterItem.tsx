import React from 'react';
import {IProductProperty} from "@/app/types/product.interface";
import {getParamsTitle} from "@/app/utils/getParamsTitle";
import {EnumParams} from "@/app/types/main.interface";
import {useFilter} from "@/app/hooks/useFilter";

interface IProps {
	title: string
	list: IProductProperty[]
	setList: (e: any) => void
}
const FilterItem = ({title, list, setList}:IProps) => {
	const filterList = useFilter()
	return (
		<div className="flex flex-col">
			<h2 className="text-secondary mt-2">{getParamsTitle(title as EnumParams)}</h2>
			{!list ? "" : list.map(item => <label key={item.id} className="mt-1">
				<input type="checkbox" className="mr-1 accent-primary" checked={filterList[title].includes(item)} onChange={()=> setList(item)}/>
				{item.name}
			</label>)}
		</div>
	);
};

export default FilterItem;