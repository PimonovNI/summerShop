import MenuList from "@/app/components/MenuList";
import {dashboardList} from "@/app/constants/dashboard.constants";
import {Metadata} from "next";


export const metadata: Metadata = {
	title: 'Dashboard',
	description: '',
}
export default function DashboardLayout({
																					children, // will be a page or nested layout
																				}: {
	children: React.ReactNode
}) {
	return (
		<section>
			<MenuList list={dashboardList}/>

			{children}
		</section>
	)
}