import React, {FC, SVGProps} from "react";

export interface IRegisterForm {
		name: string,
		email: string,
		password: string,
		confirmPassword?: string
}

export interface ILoginForm {
	name: string,
	password: string
}
