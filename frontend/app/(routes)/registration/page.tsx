"use client"
import {NextPage} from "next";
import {SubmitHandler, useForm} from "react-hook-form";
import {IRegisterForm} from "@/app/types/form.interface";
import {AiOutlineUser} from "react-icons/ai";
import {MdOutlineAlternateEmail} from "react-icons/md";
import AuthInput from "@/app/components/UI/AuthInput";
import Link from "next/link";
import Image from "next/image";
import {useTogglePassword} from "@/app/hooks/useTogglePassword";
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import {useActions} from "@/app/hooks/useActions";
import {useRouter} from "next/navigation";
import {useAuth} from "@/app/hooks/useAuth";
import {useEffect} from "react";
const Page: NextPage = () => {
	const schema = yup
		.object({
			name: yup.string()
				.required("Ім'я є обов'язковим полем")
				.min(2, "Мінімум 2 літери")
				.max(100, "Занадто багато літер")
				.matches(/[А-Яа-яA-Za-z]+/g),
			email: yup.string()
				.required("Email є обов'язковим полем")
				.email("Будь ласка введіть коректний Email"),
			password: yup.string()
				.required("Пароль є обов'язковим полем")
				.min(5, "Пароль має складатись мінімум з 5 символів")
				.max(100, "Занадто багато символів")
				.matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{5,}$/, "Пароль має складатись з літер та цифер"),
			confirmPassword: yup.string()
				.oneOf([yup.ref("password")], "Паролі не співпадають")
		})
		.required()
	const { register, handleSubmit, formState: { errors, isValid }} = useForm<IRegisterForm>({
		mode: "onChange",
		resolver: yupResolver(schema),
	})
	const auth = useActions()
	const router = useRouter()
	const userData = useAuth()
	const onSubmit: SubmitHandler<IRegisterForm> = data => {
		auth.registration({email: data.email, password: data.password, username: data.name})
	}

	useEffect(()=>{
		if (userData.message === "success") router.push("/login")
	}, [userData, router])


	const IconCss = "absolute top-4 right-4"
	const [InputType, Icon] = useTogglePassword()

	return (
		<div className="relative flex items-center justify-center h-screen">
			<form onSubmit={handleSubmit(onSubmit)} className="bg-bgColor w-[500px] mx-auto py-12 flex flex-col items-center justify-center rounded-[10px] shadow-[0px_0px_45px_0px_rgba(0,0,0,0.75)]">
				<h2 className="text-3xl text-center capitalize">Регістрація</h2>
				<AuthInput properties={register('name')}
									 labelText={"Ім'я:"}
									 type="text"
									 message={errors?.name?.message}>
					<AiOutlineUser className={IconCss}/>
				</AuthInput>
				<AuthInput properties={register('email')}
									 labelText={"Email:"}
									 type="email"
									 message={errors?.email?.message}>
					<MdOutlineAlternateEmail className={IconCss}/>
				</AuthInput>
				<AuthInput properties={register('password')}
									 labelText={"Пароль:"}
									 type={InputType}
									 message={errors?.password?.message}>
					{Icon}
				</AuthInput>
				<AuthInput properties={register('confirmPassword')}
									 labelText={"Повторіть пароль:"}
									 type={InputType}
									 message={errors?.confirmPassword?.message}>
					{Icon}
				</AuthInput>
				<p className="text-primary w-72 mt-1">{userData?.message}</p>
				<button disabled={!isValid} className=" w-1/2 mb-4 text-[18px] mt-6 rounded-full bg-primary py-2 ">Зареєструватися</button>
				<p>Вже маєте аккаунт? {<Link href={"/login"} className="underline text-blue-500">Увійти</Link>}</p>
			</form>
		</div>
	);
};

export default Page;