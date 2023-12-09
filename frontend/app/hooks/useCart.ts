import {useTypedSelector} from "@/app/hooks/useTypedSelector";

export const useCart = () => useTypedSelector((state) => state.Cart);