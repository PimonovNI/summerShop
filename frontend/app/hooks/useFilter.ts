import {useTypedSelector} from "@/app/hooks/useTypedSelector";

export const useFilter = () => useTypedSelector((state) => state.Filter);