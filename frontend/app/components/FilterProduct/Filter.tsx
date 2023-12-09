import React from 'react';
import {useCategory} from "@/app/hooks/productHooks/useCategory";
import FilterItem from "@/app/components/FilterProduct/FilterItem";
import {useBrands} from "@/app/hooks/productHooks/useBrands";
import {useSizes} from "@/app/hooks/productHooks/useSizes";
import GenderFilter from "@/app/components/FilterProduct/GenderFilter";
import PriceFilter from "@/app/components/FilterProduct/PriceFilter";
import {EnumParams} from "@/app/types/main.interface";
import {useActions} from "@/app/hooks/useActions";
import {getParamsTitle} from "@/app/utils/getParamsTitle";
import {getGenderTitle} from "@/app/utils/getGenderTitle";

const Filter = () => {
	const categoryList = useCategory()
	const brandsList = useBrands()
	const sizeList = useSizes()
	const actions = useActions()

	return (
		<div>
			<PriceFilter/>
			<FilterItem title={EnumParams.category} list={categoryList.data || []} setList={(item)=> actions.updateFilter({key: EnumParams.category, item})}/>
			<FilterItem title={EnumParams.brand} list={brandsList.data || []} setList={(item)=> actions.updateFilter({key: EnumParams.brand, item})}/>
			<FilterItem title={EnumParams.size} list={sizeList.data || []} setList={(item)=> actions.updateFilter({key: EnumParams.size, item})}/>
			<GenderFilter title={getParamsTitle(EnumParams.gender)}
										setList={(genderName)=> actions.updateFilter({key: EnumParams.gender, item: genderName})}/>
		</div>
	);
};

export default Filter;