import {useEffect} from "react";
import {getParamsTitle} from "@/app/utils/getParamsTitle";
import {EnumParams, EnumSortTitle, IParams} from "@/app/types/main.interface";
import {usePathname, useRouter} from "next/navigation";
import {useFilter} from "@/app/hooks/useFilter";
import {getGenderTitle} from "@/app/utils/getGenderTitle";
import {EnumGender} from "@/app/types/product.interface";

export const useUserParams = () => {
	const pathname = usePathname()
	const router = useRouter()
	const filter = useFilter()

	useEffect(() => {
		if (filter.minMaxPrice.maxValue !== -1 && filter.minMaxPrice.minValue !== -1)
			router.push(pathname + '?' + setParams(filter))
	}, [filter, filter.minMaxPrice.maxValue, filter.minMaxPrice.minValue]);
	const setParams =
		(params: IParams) => {
			const paramsString = new URLSearchParams();

			const keys = Object.keys(params);

			keys.forEach((key) => {
				const value = params[key];
				if (Array.isArray(value) && value.length > 0) {
					const string = value.map((item) => item.name).join(",");
					const paramKey = EnumParams[key as keyof typeof EnumParams];
					paramsString.set(getParamsTitle(paramKey), string);
				}
			});
			if(params.sort !== EnumSortTitle.new) paramsString.set(getParamsTitle(EnumParams.sort), params.sort);
			if (params.gender) {
				paramsString.set(getParamsTitle(EnumParams.gender), getGenderTitle(params.gender as EnumGender));
			}
			if (params.search) {
				paramsString.set(getParamsTitle(EnumParams.search), params.search);
			}
			if ((params.price.minValue !== filter.minMaxPrice.minValue || params.price.maxValue !== filter.minMaxPrice.maxValue)
				&& params.price.minValue !== params.price.maxValue) {
				paramsString.set(getParamsTitle(EnumParams.price), `${params.price.minValue}-${params.price.maxValue}`);
			}
			return paramsString.toString().replace(/%2C/g, ",");
		}
}
