"use client"
import React, {useState} from 'react';
import {NextPage} from "next";
import {EnumModalTitle} from "@/app/constants/dashboard.constants";
import {BiEdit} from "react-icons/bi";
import {MdOutlineDeleteOutline} from "react-icons/md";
import Image from "next/image";
import ModalsCreateEditProduct from "@/app/components/modals/ModalsCreateEditProduct";
import {useAllProducts, useDeleteProduct} from "@/app/hooks/productHooks/useAllProducts";
import SearchInput from "@/app/components/UI/SearchInput";
import Pagination from "@/app/components/UI/Pagination";
import {toPrice} from "@/app/utils/toPrice";
import Filter from "@/app/components/FilterProduct/Filter";
import AdminProductsList from "@/app/components/products/AdminProductsList";
import Loading from "@/app/(routes)/Loading";
import ActiveFilter from "@/app/components/FilterProduct/ActiveFilter";
const Products: NextPage = () => {
	const [visible, setVisible] = useState(false);
	const [modalData, setModalData] = useState({title: "", id: 0});

	return (
		<div className="container">
			<div className="mx-auto flex justify-between items-center">
				<div className="w-full">
					<ActiveFilter/>
				</div>
				<button onClick={()=>{
					setModalData({title: EnumModalTitle.Create , id: 0})
					setVisible((prevState) => !prevState)
				}} className=" w-[200px] ml-2 text-[18px] rounded-[7px] bg-primary py-2 ">Створити</button>
			</div>
			<div className="flex">
				<aside className="2xl:min-w-[220px] mr-2 ">
					<Filter />
				</aside>
				<AdminProductsList setModalData={setModalData} setVisible={setVisible}/>
			</div>
			{visible &&<ModalsCreateEditProduct modalData={modalData} handleClose={()=> setVisible((prevState) => !prevState)}/>}
		</div>
	);
};

export default Products;