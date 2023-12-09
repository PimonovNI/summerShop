import React from 'react';
import {useUserHistory} from "@/app/hooks/productHooks/useUserHistory";
import UserHistoryTopItem from "@/app/components/products/HistoryTop/UserHistoryTopItem";

const UserHistoryList = () => {
	const userHistoryList = useUserHistory();
	return (
		<UserHistoryTopItem productsList={userHistoryList.data || []} title="Історія переглядів"/>
	)
};

export default UserHistoryList;