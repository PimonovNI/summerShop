export const cartQuantity = (quantity:number) => {
	if (quantity === 1) {
		return '1 товар';
	} else if (quantity > 1 && quantity < 5) {
		return `${quantity} товара`;
	} else {
		return `${quantity} товарів`;
	}
}