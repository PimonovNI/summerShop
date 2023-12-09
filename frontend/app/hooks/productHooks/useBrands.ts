import {useQuery} from "@tanstack/react-query";
import {ProductService} from "@/app/service/product.service";

export const useBrands = () => {
	const {isLoading, data} = useQuery(
		["getBrands"],
		() => ProductService.getBrands(),
		{
			select: ({data})=> data,
			cacheTime: 1000
		}
	)
	return {isLoading, data}
}