import React from 'react';
import DropDownMenuItem from "@/app/components/UI/DropDownMenuHeader/DropDownMenuItem";
import {IDropMenuItem} from "@/app/types/main.interface";

interface IProps{
	setOpen: (bool:boolean)=> void
	list: IDropMenuItem[]
}
const DropDownMenuHeader = ({setOpen, list}:IProps) => {
	return (
		<ul>
			{list.map(item => (
				<DropDownMenuItem key={item.link}  setOpen={setOpen} item={item}/>
			))}
		</ul>
	);
};

export default DropDownMenuHeader;