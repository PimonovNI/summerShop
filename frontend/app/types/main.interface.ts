import React, {useState} from "react";
import {IProductProperty} from "@/app/types/product.interface";

export interface IDropMenuItem {
	icon: React.ReactNode
	link: string
	title: string
}

export enum EnumParams {
	category = "category",
	brand = "brand",
	size = "size",
	gender = "gender",
	price = "price",
	minMaxPrice = "minMaxPrice",
	sort = "sort",
	search = "search"
}

export interface IParams {
	[key: string]: any;
	category: IProductProperty[],
	brand: IProductProperty[],
	size: IProductProperty[],
	gender: string,
	price: {
		minValue: number,
		maxValue: number,
	},
	minMaxPrice: {
		minValue: number,
		maxValue: number,
	},
	sort: string,
	search: string
}

export interface IPriceState {
	minValue: number;
	maxValue: number;
}

export enum EnumSortTitle {
	new = "Новинка",
	cheap = "Від дешевих до дорогих",
	expensive = "Від дорогих до дешевих"
}
export enum EnumSortServerId {
	"Новинка",
	"Від дешевих до дорогих",
	"Від дорогих до дешевих"
}
