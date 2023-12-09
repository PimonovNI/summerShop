import React, {useState} from "react";
import {AiOutlineEye, AiOutlineEyeInvisible} from "react-icons/ai";


export const useTogglePassword:() => [string, React.JSX.Element] =  () =>{
	const [visible, setVisible] = useState(false)

	const Icon = visible
		? <AiOutlineEye className="absolute top-4 right-4" onClick={ ()=> setVisible(!visible)} />
		: <AiOutlineEyeInvisible className="absolute top-4 right-4" onClick={ ()=> setVisible(!visible)}/>
	const InputType = visible ? "text" : "password"

	return [InputType, Icon]
}