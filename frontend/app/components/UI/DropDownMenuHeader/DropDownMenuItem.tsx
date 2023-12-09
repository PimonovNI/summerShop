import React from 'react';
import {useRouter} from "next/navigation";
import {useActions} from "@/app/hooks/useActions";
import {IDropMenuItem} from "@/app/types/main.interface";
interface IProps{
	item: IDropMenuItem
	setOpen: (bool:boolean)=> void
}
const DropDownMenuItem = ({item, setOpen}:IProps) => {
	const router = useRouter()
	const actions = useActions()
	return (
		<li
			onClick={()=> {
				router.push(item.link)
				item.title === "Вийти" ? actions.logout() : "";
				setOpen(false)}
			}
			className="flex w-[140px] mt-2 items-center cursor-pointer">
			{item.icon}
			<span className="mr-1.5">{item.title}</span>
		</li>
	);
};

export default DropDownMenuItem;