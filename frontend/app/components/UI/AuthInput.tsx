import React, {FC, SVGProps} from 'react';
import ErrorMessage from "@/app/components/UI/ErrorMessage";

export interface IInputProps  {
	properties: any,
	labelText: string,
	children?: React.ReactNode,
	type: string,
	message: string | undefined,
}
const AuthInput = ({properties, labelText, children, type, message}: IInputProps) => {

	const LabelCss ="absolute text-sm duration-300 transform -translate-y-6 scale-75 pointer-events-none top-3 origin-[0] peer-focus:left-0 peer-focus:text-secondary peer-focus:text-[1rem] peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6"
	const InputCss = "block w-72 py-2.5 px-0 text-sm bg-transparent border-0 border-b-2 border-gray-500 appearance-none dark:focus:border-secondary focus:outline-none focus:ring-0 peer"

	return (
		<div className="relative mt-4">
			<input {...properties} className={InputCss} placeholder="" type={type}/>
			<label className={LabelCss}>{labelText}</label>
			<ErrorMessage message={message}/>
			{children}
		</div>
	);
};

export default AuthInput;