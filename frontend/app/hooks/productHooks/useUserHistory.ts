import {useQuery} from "@tanstack/react-query";
import {ProductService} from "@/app/service/product.service";
import {IProductResponse} from "@/app/types/product.interface";

export const useUserHistory = () => {
	const {isLoading, data} = useQuery(
		["getHistoryProducts"],
		() => ProductService.getHistoryProducts(),
		{
			select: ({data})=> data
		}
	)
	return {isLoading, data}
}