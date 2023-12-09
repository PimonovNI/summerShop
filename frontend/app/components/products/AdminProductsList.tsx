import React, {useState} from 'react';
import Pagination from "@/app/components/UI/Pagination";
import {useServerParams} from "@/app/hooks/Params/useServerParams";
import {useAllProducts, useDeleteProduct} from "@/app/hooks/productHooks/useAllProducts";
import {useFilter} from "@/app/hooks/useFilter";
import Loading from "@/app/(routes)/Loading";
import {EnumSortTitle} from "@/app/types/main.interface";
import AdminProductsItem from "@/app/components/products/AdminProductsItem";
import {useUserParams} from "@/app/hooks/Params/useUserParams";
import {EnumModalTitle} from "@/app/constants/dashboard.constants";

interface IProps {
	setModalData: (data: { title: EnumModalTitle, id: number }) => void;
	setVisible: (visible: boolean) => void;
}
const AdminProductsList = ({setModalData, setVisible}: IProps) => {
	const perPage = 24;
	const [firstIndex, setFirstIndex] = useState(0);
	const filterList = useFilter()
	const deleteProduct = useDeleteProduct()
	useUserParams()

	const params = useServerParams()
	const products =  useAllProducts(params || "")

	if (products.isLoading) return <Loading/>
	const sortedList = products?.data?.filter(product => product.name.toLowerCase().includes(filterList.search.toLowerCase()))
	filterList.sort === EnumSortTitle.new && sortedList?.reverse()

	const productsList = sortedList?.slice(firstIndex, firstIndex + perPage)
	return (
		<div className="w-full">
			{!sortedList?.length ? <div className="text-center">Щось пішло не так )))</div>
					:<>
						<div className="flex flex-wrap gap-3">
							{productsList?.map(product => <AdminProductsItem key={product.id} product={product} setModalData={setModalData} setVisible={setVisible} deleteProduct={deleteProduct}/>)}
						</div>
						<Pagination listLength={sortedList.length || 0} perPage={perPage} setFirstIndex={setFirstIndex}/>
					</>}
		</div>
	);
};

export default AdminProductsList;