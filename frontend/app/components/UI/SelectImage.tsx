import React, { useRef } from 'react';
import Image from 'next/image';
import {BsFillTrashFill} from "react-icons/bs";
import {LuTrash} from "react-icons/lu";

interface IProps {
	image: string | undefined;
	setImage: (image: null | File) => void;
}

const SelectImage = ({ image, setImage }: IProps) => {
	const filePicker = useRef<HTMLInputElement | null>(null);

	const handlePick = () => {
		if (filePicker.current) {
			filePicker.current.click();
		}
	};
	const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		if (e.target.files && e.target.files[0]) {
			setImage(e.target.files[0]);
		}
	};
	return (
		<div className="mb-6 w-full flex items-start flex-col">
			<div className="flex">
				<h2>Фото:</h2>
			</div>
			<div className="relative">
				<Image
					onClick={handlePick}
					className="mt-1 rounded-xl shadow hover:border-secondary border-[1px] max-h-[200px] w-auto"
					src={image || ''}
					width={200}
					height={200}
					alt="product_image"
				/>
				{image !== "/images/noImage.png" && <LuTrash onClick={() => setImage(null)} className="absolute text-2xl top-1 right-0 rounded-[5px] p-1 text-white bg-primary"/>}
			</div>
			<input
				className="hidden"
				type="file"
				ref={filePicker}
				accept="image/*"
				onChange={handleFileChange}
			/>
		</div>
	);
};

export default SelectImage;
