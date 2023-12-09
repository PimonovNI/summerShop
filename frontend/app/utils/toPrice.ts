export const toPrice = (price:number) => price.toLocaleString('uk-UA', {
	minimumFractionDigits: 0,
	maximumFractionDigits: 0
}) + "₴"