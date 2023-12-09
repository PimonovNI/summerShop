import React from 'react';
import { ButtonGroupProps } from 'react-multi-carousel/lib/types';
import {MdOutlineNavigateBefore, MdOutlineNavigateNext} from "react-icons/md";

interface CarouselButtonGroupProps extends ButtonGroupProps {
	className?: string;
}
const ProductsCarouselButton = ({ next, previous, ...rest }:CarouselButtonGroupProps) => {
	// @ts-ignore
	const { carouselState: { totalItems, slidesToShow, currentSlide } } = rest;

	return (
		<div className="carousel-button-group flex absolute top-1 right-1">
			<MdOutlineNavigateBefore onClick={() => previous && typeof previous === 'function' && previous()}
															 className={`text-xl cursor-pointer ${currentSlide === 0 ? 'opacity-40' : ''}`}/>
			<MdOutlineNavigateNext onClick={() => next && typeof next === 'function' && next()}
														 className={`text-xl cursor-pointer ${currentSlide + slidesToShow === totalItems ? 'opacity-40' : ''}`}/>
		</div>
	);
};
export default ProductsCarouselButton;