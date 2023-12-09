"use client"
import React from 'react';
import Link from "next/link";
import {usePathname} from "next/navigation";
interface IList {
	title: string,
	link: string
}
type TypeProps = {
	list: IList[]
}
const MenuList: React.FC<TypeProps> = ({list}) => {
	const pathname = usePathname()
	return (
		<div className="flex items-center justify-center mt-3">
			{list.map(item => (
				<Link href={item.link} key={item.title} className={`mr-5 text-[20px] active:text-secondary 
				${pathname === item.link ? "text-secondary" : ""}`}
				>{item.title}</Link>
			))}
		</div>
	);
};

export default MenuList;