import {EnumGender} from "@/app/types/product.interface";
export const genders = [EnumGender.man, EnumGender.woman, EnumGender.child]

export const resetModal = {
	id: 0,
	name: "",
	photo: "/images/noImage.png",
	description: "",
	price: 0,
	brand: {
		id: -1,
		name: ""
	},
	category: {
		id: -1,
		name: ""
	},
	gender: "MAN",
	size: []
}