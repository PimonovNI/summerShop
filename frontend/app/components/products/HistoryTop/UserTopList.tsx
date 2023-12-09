import React from 'react';
import UserHistoryTopItem from "@/app/components/products/HistoryTop/UserHistoryTopItem";
import {useTopProducts} from "@/app/hooks/productHooks/useTopProducts";

const UserTopList = () => {
	const userTopProductList = useTopProducts();
	return (
		<UserHistoryTopItem productsList={userTopProductList.data || []} title="Спеціально для вас"/>
	)
};

export default UserTopList;