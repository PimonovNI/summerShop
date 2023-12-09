import React from 'react';
import {TiDeleteOutline} from "react-icons/ti";

interface IProps {
	title: string,
	value: string | number,
	type?: string,
	setValue: (e:any)=> void,
	className?: string
}
const Input = ({title, value, type="text", setValue, className}: IProps) => {
	return (
		<div className={`flex relative rounded-full h-8 items-center justify-center ${className}`}>
			<input type={type}
						 placeholder=""
						 className="w-full text-[20px] px-1.5 appearance-none custom-input bg-transparent border border-gray-300 rounded-md hover:border-gray-500 block  py-2.5 text-sm border-b-2 dark:focus:border-secondary focus:outline-none focus:ring-0 peer"
						 value={value}
						 onChange={(e)=>setValue(e.target.value)}/>
			<TiDeleteOutline onClick={()=>setValue(type === "number" ? 0 : "")}
											 className="absolute top-2 right-4"/>
			<label className="absolute pointer-events-none -top-[20px] bg-bgColor origin-[0] left-3 peer-focus:text-secondary">
				{title}</label>
		</div>
	);
};

export default Input;