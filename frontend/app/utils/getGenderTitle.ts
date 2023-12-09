import {EnumGender} from "@/app/types/product.interface";

const genders = {
		[EnumGender.man]: "Чоловіче",
		[EnumGender.woman]: "Жіноче",
		[EnumGender.child]: "Дитяче",
	}

export const getGenderTitle = (gender: EnumGender): string => genders[gender]