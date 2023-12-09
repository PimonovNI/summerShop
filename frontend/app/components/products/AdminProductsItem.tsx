import React from 'react';
import Image from "next/image";
import { toPrice } from "@/app/utils/toPrice";
import { EnumModalTitle } from "@/app/constants/dashboard.constants";
import { BiEdit } from "react-icons/bi";
import { MdOutlineDeleteOutline } from "react-icons/md";
import { IProductResponse } from "@/app/types/product.interface";

interface IProps {
	product: IProductResponse;
	setModalData: (data: { title: EnumModalTitle, id: number }) => void;
	setVisible: (visible: boolean) => void;
	deleteProduct: { mutate: (productId: number) => void };
}

const AdminProductsItem = ({ product, setModalData, setVisible, deleteProduct }: IProps) => {
	return (
		<div className="p-2 shadow rounded-xl flex flex-col w-[200px] h-[350px]">
			<div className="flex-grow flex justify-center items-center">
				<Image src={product.photo} alt={product.name} width={150} height={150} priority/>
			</div>
			<div className="flex flex-col justify-end">
				<p className="text-[18px] mt-1 overflow-hidden h-10 line-clamp-2">{product.name}</p>
				<p className="text-xs text-textSecondary overflow-hidden h-4">{product.brand}</p>
				<p className="my-1 font-bold">{toPrice(product.price)}</p>
				<div className="flex justify-center">
					<div
						className="h-7 text-[15px] flex items-center flex-wrap overflow-hidden border p-1 rounded-[7px] mr-2 cursor-pointer justify-start"
						onClick={() => {
							setModalData({ title: EnumModalTitle.Edit, id: product.id });
							setVisible(true);
						}}
					>
						<BiEdit name="Редагувати" className="text-xl mr-0.5 text-blue-500" />
						Edit
					</div>
					<div
						className="h-7 text-[15px] flex items-center flex-wrap overflow-hidden border p-1 rounded-[7px] mr-2 cursor-pointer justify-start"
						onClick={() => deleteProduct.mutate(product.id)}>
						<MdOutlineDeleteOutline name="Видалити" className="text-xl mr-0.5 text-primary" />
						Delete
					</div>
				</div>
			</div>
		</div>
	);
};

export default AdminProductsItem;
