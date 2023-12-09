import {EnumParams} from "@/app/types/main.interface";

const params = {
	[EnumParams.category]: "Категорія",
	[EnumParams.brand]: "Бренд",
	[EnumParams.size]: "Розміри",
	[EnumParams.gender]: "Стать",
	[EnumParams.price]: "Ціна",
	[EnumParams.minMaxPrice]: "Ціна",
	[EnumParams.sort]: "Сортування",
	[EnumParams.search]: "Пошук",

}

export const getParamsTitle = (param: EnumParams): string => params[param];
