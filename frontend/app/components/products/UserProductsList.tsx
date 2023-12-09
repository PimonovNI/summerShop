import React, { useState} from 'react';
import Pagination from "@/app/components/UI/Pagination";
import Loading from "@/app/(routes)/Loading";
import {useAllProducts} from "@/app/hooks/productHooks/useAllProducts";
import {useFilter} from "@/app/hooks/useFilter";
import {EnumSortTitle} from "@/app/types/main.interface";
import UserProductsItem from "@/app/components/products/UserProductsItem";
import {useServerParams} from "@/app/hooks/Params/useServerParams";

const UserProductsList = () => {
	const perPage = 28;
	const [firstIndex, setFirstIndex] = useState(0);
	const filterList = useFilter()

	const params = useServerParams()
	const products =  useAllProducts(params || "")

	if (products.isLoading) return <Loading/>
	const sortedList = products?.data?.filter(product => product.name.toLowerCase().includes(filterList.search.toLowerCase()))
	filterList.sort === EnumSortTitle.new && sortedList?.reverse()

	const productsList = sortedList?.slice(firstIndex, firstIndex + perPage)
	return (
		<div>
				{!sortedList?.length ? <div className="text-center">Нічого не знайдено</div>
					:
					<div className="flex justify-center flex-col">
						<div className="flex flex-wrap gap-3">
								{productsList?.map((product) => <UserProductsItem product={product} key={product.id}/>)}
						</div>
						<Pagination listLength={sortedList.length || 0} perPage={perPage} setFirstIndex={setFirstIndex}/>
					</div>}
		</div>
	);
};

export default UserProductsList;