import React, {useState} from 'react';
import {TiDeleteOutline} from "react-icons/ti";
interface IProps {
	title: string,
	isAddOther?: boolean,
	options: string[],
	selectOption: string,
	setOptions: (e:string) => void,
	className?: string
}
const Select = ({title, isAddOther = false, options, selectOption, className,  setOptions}: IProps) => {
	const [isNew, setIsNew] = useState(false)
	return (
		<div>
			{!isNew ?
				<div className="relative">
					<select
						className={`${className} w-[120px] text-[20px] px-1.5 appearance-none bg-bgColor border border-gray-300 rounded-md hover:border-gray-500 block  py-2.5 text-sm bg-transparent  border-b-2  dark:focus:border-secondary focus:outline-none focus:ring-0 peer`}
						onChange={(e)=>{
							if (e.target.value === "other") setIsNew(true)
							setOptions(e.target.value)
						}}
						value={selectOption}
					>
						{options.map((option, index) => (
							<option key={index} value={option}>
								{option}
							</option>
						))}
						{isAddOther && <option value="other">Інше</option>}
					</select>
					<label className="bg-bgColor px-0.5 absolute duration-300 transform -translate-y-6 scale-75 pointer-events-none top-3 origin-[0] left-1.5 peer-focus:text-secondary peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
						{title}</label>
				</div>
				: isAddOther &&(
					<div className="flex relative rounded-full px-1 items-center justify-center">
						<input type="text" placeholder="Новий..."
									 className={`${className} w-[120px] text-[20px] px-1.5 appearance-none bg-transparent border border-gray-300 rounded-md hover:border-gray-500 block  py-2.5 text-sm border-b-2 dark:focus:border-secondary focus:outline-none focus:ring-0 peer`}
									 onChange={(e)=>setOptions(e.target.value)}/>
						<TiDeleteOutline onClick={()=>{
																			setOptions(options[0])
																			setIsNew(false)}}
														 className="absolute top-3.5 right-8"/>
						<label className="absolute -top-[12px] scale-75 pointer-events-none  bg-bgColor px-1 origin-[0] left-1.5 peer-focus:text-secondary ">
							{title}</label>
					</div>
				)}
		</div>
	);
};

export default Select;