import React from 'react';
import UserProductsItem from "@/app/components/products/UserProductsItem";
import {IProductResponse} from "@/app/types/product.interface";
import Carousel from "react-multi-carousel";
import 'react-multi-carousel/lib/styles.css';
import ProductsCarouselButton from "@/app/components/products/HistoryTop/ProductsCarouselButton";
interface IProps {
	productsList: IProductResponse[]
	title: string
}
const responsive = {
	desktop: {
		breakpoint: {
			max: 3000,
			min: 1536
		},
		items: 7,
		partialVisibilityGutter: 40
	},
	tabletToDesktop: {
		breakpoint: { max: 1536, min: 1280 },
		items: 6,
	},
	tablet: {
		breakpoint: {
			max: 1280,
			min: 1025
		},
		items: 4,
		partialVisibilityGutter: 30
	},
	mobileToTablet: {
		breakpoint: {
			max: 1025,
			min: 768
		},
		items: 3,
		partialVisibilityGutter: 30
	},
	mobile: {
		breakpoint: {
			max: 768,
			min: 0
		},
		items: 2,
		partialVisibilityGutter: 30
	},
}
const UserHistoryTopItem = ({ productsList, title }: IProps) => {
	return (
		<div className="relative">
			<>
				<h2 className="mt-2 text-xl font-semibold">{title}</h2>
				<Carousel additionalTransfrom={0}
									arrows={false}
									autoPlaySpeed={3000}
									centerMode={false}
									className=""
									containerClass="container-padding-bottom"
									customButtonGroup={<ProductsCarouselButton />}
									draggable={false}
									focusOnSelect={false}
									infinite={false}
									itemClass=""
									keyBoardControl
									minimumTouchDrag={80}
									pauseOnHover
									renderArrowsWhenDisabled={false}
									renderButtonGroupOutside
									renderDotsOutside={false}
									responsive={responsive}
									rewind={false}
									rewindWithAnimation={false}
									rtl={false}
									shouldResetAutoplay
									showDots={false}
									sliderClass=""
									slidesToSlide={1}
									swipeable>
					{productsList?.map((product) => (
						<div key={product.id} className="my-1">
							<UserProductsItem product={product} />
						</div>
					))}
				</Carousel>
			</>
		</div>
	);
};

export default UserHistoryTopItem;