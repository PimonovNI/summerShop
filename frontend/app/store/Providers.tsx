"use client";

import { store } from "./store";
import { Provider } from "react-redux";
import {QueryClientProvider} from "@tanstack/react-query";
import {QueryClient} from "@tanstack/query-core";
const queryClient = new QueryClient({
	defaultOptions:{
		queries:{
			refetchOnWindowFocus: false
		}
	}
})

export function Providers({ children }: { children: React.ReactNode }) {
	return <Provider store={store}><
		QueryClientProvider client={queryClient}>
			{children}
		</QueryClientProvider>
	</Provider>;
}