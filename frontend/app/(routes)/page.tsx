"use client"
import React from "react";
import Filter from "@/app/components/FilterProduct/Filter";
import {useUserParams} from "@/app/hooks/Params/useUserParams";
import ActiveFilter from "@/app/components/FilterProduct/ActiveFilter";
import {useSearchParams} from "next/navigation";
import {useAuth} from "@/app/hooks/useAuth";
import UserProductsList from "@/app/components/products/UserProductsList";
import UserHistoryList from "@/app/components/products/HistoryTop/UserHistoryList";
import UserTopList from "@/app/components/products/HistoryTop/UserTopList";
export default function Home() {
  const { user } = useAuth();
  useUserParams();
  const searchParams = useSearchParams().size > 0;

  return (
    <div className="container mt-2">
      <ActiveFilter />
      <section className="flex">
        <aside className="2xl:min-w-[220px] mr-2 ">
          <Filter />
        </aside>
        <div className="flex-auto overflow-y-auto">
          {!searchParams && user && (
           <>
             <UserHistoryList/>
             <UserTopList/>
           </>
          )}
          <>
            {!searchParams && user && <h2 className="mt-2 text-xl font-semibold">Перелік товарів</h2>}
            <UserProductsList/>
          </>
        </div>
      </section>
    </div>
  );
}



