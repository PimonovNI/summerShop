import React from 'react';
import {genders} from "@/app/constants/product.constants";
import {getGenderTitle} from "@/app/utils/getGenderTitle";
import {useFilter} from "@/app/hooks/useFilter";

interface IProps {
	title: string
	setList: (e: any) => void
}
const GenderFilter = ({title, setList}:IProps) => {
	const filterList = useFilter()
	return (
		<div className="flex flex-col">
			<h2 className="text-secondary mt-2">{title}</h2>
			{genders.map(gender => <label key={gender} className="mt-1">
				<input type="radio" name="gender"
							 className="custom-radio"
							 checked={filterList.gender === gender}
							 onChange={()=> setList(gender)}/>
				<span className="custom-radio-label">
					{getGenderTitle(gender)}
				</span>
			</label>)}
		</div>
	);
};

export default GenderFilter;