import React from 'react';
import {RxCross2} from "react-icons/rx";
import {EnumParams, EnumSortTitle} from "@/app/types/main.interface";
import {useFilter} from "@/app/hooks/useFilter";
import {useActions} from "@/app/hooks/useActions";
import {useMinMaxPrice} from "@/app/hooks/useMinMaxPrice";
import {useSearchParams} from "next/navigation";
import {getGenderTitle} from "@/app/utils/getGenderTitle";
import {EnumGender} from "@/app/types/product.interface";
import SortFilter from "@/app/components/FilterProduct/SortFilter";

const ActiveFilter = () => {
	const filterList = useFilter()
	const actions = useActions()
	const price = useMinMaxPrice()
	const keys = Object.keys(filterList);
	const searchParams = useSearchParams()
	const itemClassName = "flex whitespace-nowrap border-secondary p-2 border mr-1 rounded-3xl justify-center items-center cursor-pointer"
	return (
		<section className="flex my-2 justify-between">
			<div className="flex overflow-x-auto w-full mr-1.5">
				{searchParams.size > 0 &&
					<span  onClick={()=>actions.reset()}
								 className={itemClassName}>
					Скинути</span>}
				{
					keys.map((key) => {
						const value = filterList[key];
						if (Array.isArray(value) && value.length > 0) {
							return value.map(item =>
								<span key={item.id}
											onClick={()=>actions.updateFilter({key, item})}
											className={itemClassName}>
                {item.name}
								<RxCross2 className="ml-1"/>
								</span>)
						}
						else if (key === EnumParams.gender && filterList[key])
							return <span key={key}
													 onClick={()=>
														 actions.updateFilter({key, item: ""})
													 }
													 className={itemClassName}>
              {getGenderTitle(filterList[key] as EnumGender)}<RxCross2 className="ml-1"/></span>
						else if (key === EnumParams.search && filterList[key])
							return <span key={key}
													 onClick={()=>
														 actions.updateFilter({key, item: ""})
													 }
													 className={itemClassName}>
              {filterList[key]}<RxCross2 className="ml-1"/></span>
						else if (key === EnumParams.sort && filterList[key] && filterList[key] !== EnumSortTitle.new)
							return <span key={key}
													 onClick={()=>
														 actions.updateFilter({key, item: EnumSortTitle.new})
													 }
													 className={itemClassName}>
              	{filterList[key] as EnumSortTitle}
								<RxCross2 className="ml-1"/>
							</span>
						if (key === EnumParams.price && (filterList.price.minValue !== price.minValue || filterList.price.maxValue !== price.maxValue)
							&& filterList.price.minValue !== filterList.price.maxValue)
							return <span key={key}
													 onClick={()=>{
														 actions.updateFilter({key, item: {
																 minValue: price.minValue,
																 maxValue: price.maxValue}})
													 }}
													 className={itemClassName}>
              	{`${filterList.price.minValue}-${filterList.price.maxValue}`}
								<RxCross2 className="ml-1"/>
							</span>
					})
				}
			</div>
			<SortFilter/>
		</section>
	);
};

export default ActiveFilter;