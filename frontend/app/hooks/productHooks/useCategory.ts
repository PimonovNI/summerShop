import {useQuery} from "@tanstack/react-query";
import {ProductService} from "@/app/service/product.service";

export const useCategory = () => {
	const {isLoading, data} = useQuery(
		["getCategory"],
		() => ProductService.getCategory(),
		{
			select: ({data})=> data,
			cacheTime: 1000
		}
	)
	return {isLoading, data}
}