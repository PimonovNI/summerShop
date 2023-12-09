import {toast} from "react-toastify";

export const errorNotify = (title:string) => toast.error(title, {
	position: "top-center",
	autoClose: 2000,
	hideProgressBar: true,
	closeOnClick: true,
	pauseOnHover: true,
	draggable: true,
	progress: undefined,
	theme: "light",
});