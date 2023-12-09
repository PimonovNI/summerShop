import type {NextRequest} from 'next/server'
import {NextResponse} from 'next/server'
import {EnumRoles, EnumSaveData} from "@/app/types/user.interface";

export function middleware(request: NextRequest) {
	const userCookie = request.cookies.get(EnumSaveData.user);
	const { pathname } = request.nextUrl

	if (userCookie?.value) {
		const user = JSON.parse(userCookie.value);
		if (user.role !== EnumRoles.ADMIN) {
			if (pathname.startsWith('/dashboard')) {
				return NextResponse.redirect(new URL('/', request.url));
			}
		}
	}

}

