import {ImProfile} from "react-icons/im";
import {FiLogOut} from "react-icons/fi";
import {MdLocalShipping} from "react-icons/md";
import {EnumSortTitle} from "@/app/types/main.interface";
export const dropDownMenuHeader = [
	{
		icon: <ImProfile className="mr-1.5"/>,
		title: "Мій Профіль",
		link: "/user/profile"
	},
	{
		icon: <MdLocalShipping className="mr-1.5"/>,
		title: "Мой закази",
		link: "/user/orders"
	},
	{
		icon: <FiLogOut className="mr-1.5"/>,
		title: "Вийти",
		link: "/login",
		type: "br"
	},
]
export const dropDownFilter = [
	{
		title: EnumSortTitle.new,
	},
	{
		title: EnumSortTitle.cheap,
	},
	{
		title: EnumSortTitle.expensive,
	},
]

export const paymentMethod = ["Оплата зараз", "При отриманні"]
export const paymentDelivery = ["Самовивіз з магазину", "Укр пошта", "Нова пошта", "Кур'єр"]

