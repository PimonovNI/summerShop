export interface IProduct {
	id: number,
	name: string,
	photo?: string,
	description: string,
	price: number,
	brand: {
		id: number,
		name: string
	},
	category: {
		id: number,
		name: string
	},
	gender: string,
	size: IProductProperty[]
}
export interface IProductResponse {
	id: number,
	name: string,
	photo: string,
	brand: string,
	price: number
}
export enum EnumGender {
	man = "MAN",
	woman = "WOMAN",
	child = "CHILD",
}

export interface IProductProperty {
	id: number,
	name: string,
	count?: number
}

export interface IFindId {
	id: number,
	name: string
	count?: number
}

export interface IProductCart {
	isLoading: boolean
	products: IProductCartItem[]
	total: number
	quantity: number
}
export interface IProductCartItem {
	id: number,
	name: string,
	photo: string,
	brand: string,
	price: number
	size: string,
	count: number
}
export interface ICartAdd {
	product: IProductCartItem,
	request: {
		product: number,
		count: number,
		size: number
	}
}