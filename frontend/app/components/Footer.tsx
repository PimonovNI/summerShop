"use client"
import React from 'react';
import Logo from "@/app/components/Logo";
import Link from "next/link";
import {FaCcMastercard, FaCcPaypal, FaCcVisa, FaMapMarkerAlt} from "react-icons/fa";
import {MdEmail, MdPhone} from "react-icons/md";
import {FaInstagram, FaTelegram, FaViber, FaXTwitter} from "react-icons/fa6";

const Footer = () => {
	return (
		<div className="bg-bgFooter text-textSecondary pt-5 text-sm">
			<div className="w-2/3 flex flex-wrap justify-center mx-auto ">
				<div className="flex-1 flex justify-center items-center min-w-[250px]">
					<Logo/>
				</div>
				<div className="flex-1 min-w-[250px]">
					<h2 className="text-base text-secondary">Summer Shop</h2>
					Магазин літньої моди пропонує найкращий вибір стильного одягу для спекотних днів. Дивіться наші нові колекції та тренди сезону.
					<div className="flex gap-2 text-3xl mt-1.5">
						<FaCcVisa/>
						<FaCcMastercard/>
						<FaCcPaypal/>
					</div>
				</div>
				<div className="flex-1 min-w-[250px]">
					<h2 className="text-base text-secondary">Посилання:</h2>
					<div className="flex flex-col gap-1">
						<Link href="#">Про нас</Link>
						<Link href="#">Контакти</Link>
						<Link href="#">Доставка та повернення</Link>
						<Link href="#">Політика конфіденційності</Link>
					</div>
				</div>
				<div className="flex-1 min-w-[250px]">
					<h2 className="text-base text-secondary">Контактна інформація:</h2>
					<div className="flex flex-col gap-1">
						<Link href="tel:0567455596" className="flex items-center">
							<MdPhone className="mr-1"/>Телефон: (056) 745-55-96
						</Link>
						<span className="flex items-center">
							<MdEmail className="mr-1"/>Email: umsf.pk@gmail.com
						</span>
						<span>
							<span className="flex items-center">
								<FaMapMarkerAlt className="mr-1"/>Адреса: 49000, м.Дніпро,
							</span>
							вул. Володимира Вернадського 2/4
						</span>
					</div>
					<div className="flex gap-2 text-2xl mt-1.5">
						<Link href="#" className="hover:text-secondary"><FaInstagram/></Link>
						<Link href="#" className="hover:text-blue-400"><FaTelegram/></Link>
						<Link href="#" className="hover:text-purple-400"><FaViber/></Link>
						<Link href="#" className="hover:text-blue-400"><FaXTwitter/></Link>
					</div>
				</div>
			</div>
			<div className="text-center py-0.5 mt-3 bg-gray-900">
				<p className="text-sm">All Rights Reserved &copy; 2023 Mega Coders<sup className="text-xs">™</sup></p>
			</div>
		</div>
	);
};

export default Footer;