import {useQuery} from "@tanstack/react-query";
import {ProductService} from "@/app/service/product.service";

export const useProductDetail = (id:number, userId: number = -1) => {
	const {isLoading, data} = useQuery(
		["ProductDetail"],
		() => ProductService.getProductDetail(id, userId),
		{
			select: ({data})=> data
		}
	)
	return {isLoading, data}
}