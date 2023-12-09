import { useEffect, useState } from "react";
import { EnumParams, IPriceState } from "@/app/types/main.interface";
import { useAllProducts } from "@/app/hooks/productHooks/useAllProducts";
import { useActions } from "@/app/hooks/useActions";

export const useMinMaxPrice = () => {
	const products = useAllProducts();
	const actions = useActions();
	const [price, setPrice] = useState<IPriceState>({
		minValue: -1,
		maxValue: -1,
	});

	useEffect(() => {
		if (!products.isLoading && products.data) {
			const prices = products.data.map((product) => product.price);
			const newMinPrice = Math.min(...prices);
			const newMaxPrice = Math.max(...prices);
			setPrice({
				minValue: newMinPrice,
				maxValue: newMaxPrice,
			});
			actions.updateFilter({
				key: EnumParams.price,
				item: { minValue: newMinPrice, maxValue: newMaxPrice },
			});
			actions.updateFilter({
				key: EnumParams.minMaxPrice,
				item: { minValue: newMinPrice, maxValue: newMaxPrice },
			});
		}
	}, [products.isLoading, products.data]);

	return price;
};
