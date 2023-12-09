import SizeItem from "@/app/components/SizeItem";
import {IProductProperty} from "@/app/types/product.interface";

interface IProps {
	sizes: IProductProperty[],
	sizesCount: IProductProperty[],
	changeSizeCount: (size: IProductProperty, newCount: number) => void
}
const SizeList = ({ sizes, sizesCount, changeSizeCount }: IProps) => {
	const findSizeCount = (sizeName:string, data: IProductProperty[]) => data && data.find(item => item.name === sizeName)

	return (
		<div className="max-w-[550px]">
			<h2>Розміри:</h2>
			<div className="grid grid-cols-1 md:grid-cols-3 gap-3">
				{sizes.map(size => {
					const count = findSizeCount(size.name, sizesCount)?.count || 0;
					return (
						<div className="flex justify-between items-center mt-3 mr-6" key={size.id}>
							<span className="mr-1">{size.name}</span>
							<SizeItem
								size={size}
								changeSizeCount={changeSizeCount}
								count={count}
							/>
						</div>
					);
				})}
			</div>
		</div>
	);
}

export default SizeList;