import React, {useState} from 'react';
interface IProps {
	listLength: number,
	perPage: number,
	setFirstIndex: (index:number)=> void
}
const Pagination = ({listLength, perPage, setFirstIndex}: IProps) => {
	const [page, setPage]= useState(1)
	const totalPages = Array.from({ length: Math.ceil(listLength / perPage) }, (_, index) => index + 1);
	return (
		<div className="flex my-2 justify-center">
			<button disabled={page === 1}
							className={`${page === 1 ? "bg-bgColor" : ""} border border-b-blue-100 rounded-l p-1.5`}
							onClick={()=> {
								setPage(prevState => prevState-1)
								setFirstIndex((page-1)*perPage - perPage)}}>Попер.</button>
			{totalPages.map(pageIndex =>
				<button key={pageIndex}
								disabled={page === pageIndex}
								className={`${page === pageIndex ? "bg-blue-200" : ""} border border-b-blue-100 p-1.5`}
								onClick={()=> {
									setPage(pageIndex)
									setFirstIndex(pageIndex*perPage - perPage)
								}}>
					{pageIndex}
				</button>)}
			<button disabled={page === totalPages[totalPages.length-1]}
							className={`${page === totalPages[totalPages.length-1] ? "bg-bgColor" : ""} border border-b-blue-100 rounded-r p-1.5`}
							onClick={()=> {
								setPage(prevState => prevState+1)
								setFirstIndex((page+1)*perPage - perPage)}}>Наст.</button>
		</div>
	);
};

export default Pagination;