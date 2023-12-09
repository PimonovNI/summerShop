/** @type {import('next').NextConfig} */
const nextConfig = {
	images: {
		domains: ['summershop-production.up.railway.app','https://summer-shop-v1.vercel.app', "localhost"],
	},
	skipTrailingSlashRedirect: true,
	skipMiddlewareUrlNormalize: true
}

module.exports = nextConfig
