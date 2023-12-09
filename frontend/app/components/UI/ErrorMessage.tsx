import React from 'react';

interface IErrorMessage {
	message:string | undefined
}
const ErrorMessage = ({message}: IErrorMessage) => {
	return (
		<p className="text-primary w-72 mt-1">{message !== "success" ? message : ""}</p>
	);
};

export default ErrorMessage;