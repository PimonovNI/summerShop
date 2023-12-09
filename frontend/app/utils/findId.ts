import {IFindId} from "@/app/types/product.interface";

export const findId = (name:string, data:IFindId[]) => data && data.find(item => item.name === name)