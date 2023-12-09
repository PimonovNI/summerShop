"use client"
import React, {useState} from 'react';
import {useAuth} from "@/app/hooks/useAuth";
import {NextPage} from "next";
import {useCart} from "@/app/hooks/useCart";
import Input from "@/app/components/UI/Input";
import CartProductList from "@/app/components/products/CartProductList";
import {paymentDelivery, paymentMethod} from "@/app/constants/main.constants";
import {cartQuantity} from "@/app/utils/cartQuantity";
import {toPrice} from "@/app/utils/toPrice";
import {useActions} from "@/app/hooks/useActions";
import {useRouter} from "next/navigation";
import {successNotify} from "@/app/utils/notification/successNotify";

const Page:NextPage = () => {
	const { user } = useAuth()
	const cart = useCart()
	const action = useActions()
	const router = useRouter()
	const [paymentData, setPaymentData] = useState({
		name: user?.username,
		phone: "",
		email: user?.email,
		method: paymentMethod[0],
		address: paymentDelivery[0],
	})
	const handlePayment = () =>{
		action.sendPayment()
		router.push("/")
		successNotify("Замовлення прийняте, чекайте на дзвінок")
	}
	return (
		<div className="container">
			<h2 className="mt-5 text-xl font-semibold">Оформлення заказу</h2>
			<div className="flex gap-3">
				<div className="w-3/4">
					<div className="mt-2 ml-2 bg-bgColor rounded p-5">
						<h2 className="text-base font-semibold">Ваші данні</h2>
						<Input title={"Ім'я"} value={paymentData.name || ""}
									 setValue={(value)=> setPaymentData(prevState => ({...prevState, name: value}))}
									 className="max-w-[250px] mt-6"/>
						<Input title={"Пошта"} value={paymentData.email || ""}
									 setValue={(value)=> setPaymentData(prevState => ({...prevState, email: value}))}
									 className="max-w-[250px] mt-6" type={"email"}/>
						<Input title={"Телефон"} value={paymentData.phone}
									 setValue={(value)=> setPaymentData(prevState => ({...prevState, phone: value}))}
									 className="max-w-[250px] mt-6" type={"tel"}/>
					</div>
					<div className="mt-2 ml-2 bg-bgColor rounded p-5">
						<h2 className="text-base font-semibold">Замовлення</h2>
						<div className="flex-auto overflow-y-auto">
							<CartProductList cart={cart.products}/>
						</div>
					</div>
					<div className="mt-2 ml-2 bg-bgColor rounded p-5">
						<h2 className="text-base font-semibold">Доставка</h2>
						<div className="flex flex-col">
							{paymentDelivery.map(delivery => <label key={delivery} className="mt-1">
								<input type="radio" name="delivery"
											 className="custom-radio"
											 checked={paymentData.address === delivery}
											 onChange={()=> setPaymentData(prevState => ({...prevState, address: delivery}))}/>
								<span className="custom-radio-label">
								{delivery}
							</span>
							</label>)}
						</div>
					</div>
					<div className="mt-2 ml-2 bg-bgColor rounded p-5">
						<h2 className="text-base font-semibold">Оплата</h2>
						<div className="flex flex-col">
							{paymentMethod.map(method => <label key={method} className="mt-1">
								<input type="radio" name="method"
											 className="custom-radio"
											 checked={paymentData.method === method}
											 onChange={()=> setPaymentData(prevState => ({...prevState, method}))}/>
								<span className="custom-radio-label">
								{method}
							</span>
							</label>)}
						</div>
					</div>
				</div>
				<div className="w-1/4 bg-bgColor rounded mt-2 pl-2">
					<h2 className="text-2xl font-semibold my-4">Разом</h2>
					<div className="flex justify-between items-center mb-2">
						<span>{cartQuantity(cart.quantity)} на суму</span>
						<span className="w-[50%] text-center">{toPrice(cart.total)}</span>
					</div>
					<div className="flex justify-between items-center mb-2">
						<span>Вартість доставки</span>
						<span className="font-semibold max-w-[50%] text-center">За тарифом перевізника</span>
					</div>
					<div className="mt-20 mb-5 py-4 pl-1 mr-2 border-t border-secondary border-b flex justify-between items-center">
						<span>До сплати</span>
						<span className="w-[50%] text-center text-xl font-medium">{toPrice(cart.total)}</span>
					</div>
					<div className="text-center">
						<button className="w-[80%] text-[18px] rounded-[7px] bg-primary py-3" onClick={handlePayment}>Підтвердити</button>
					</div>
					<div className="mt-10 text-sm text-textSecondary mx-3 flex flex-col gap-3">
						<p>Отримання замовлення від 5 000 ₴ - 30 000 ₴ за наявності документів. При оплаті готівкою від 30 000 грн. необхідно надати документи для верифікації відповідно до вимог Закону України від 06.12.2019 №361-IX</p>
						<ul className="list-disc">Підтверджуючи замовлення, я приймаю умови:
							<li className="mx-3">положення про збирання та захист персональних даних</li>
							<li className="mx-3">угоди користувача</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Page;