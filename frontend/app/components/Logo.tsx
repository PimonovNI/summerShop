import React from 'react';
import Image from "next/image";
import {useRouter} from "next/navigation";

const Logo = () => {
	const router = useRouter();

	return (
		<Image onClick={() => router.push('/')}
					 src={'/images/summer-shop-logo.png'}
					 alt={"Logo"}
					 width={785/7}
					 height={316/7}
					 priority
					 className="cursor-pointer"
		/>
	);
};

export default Logo;