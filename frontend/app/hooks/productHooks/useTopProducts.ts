import {useQuery} from "@tanstack/react-query";
import {ProductService} from "@/app/service/product.service";

export const useTopProducts = () => {
	const {isLoading, data} = useQuery(
		["getTopProducts"],
		() => ProductService.getTopProducts(),
		{
			select: ({data})=> data,
			cacheTime: 10000
		}
	)
	return {isLoading, data}
}