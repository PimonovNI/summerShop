import type { Config } from 'tailwindcss'

const twColors = require('tailwindcss/colors')

const colors ={
    primary: '#f45454',
    secondary: '#b55904',
    textSecondary: '#919191',
    bgColor: '#ececec',
    bgFooter: '#303337',
    gray: twColors.gray,
    blue: twColors.blue,
    purple: twColors.purple,
    white: twColors.white,
    transparent: twColors.transparent,
}

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    colors,
    container: {
      center: true
    },
    extend: {
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
      flex: {
        '2': '2 2 0%',
        '50': '1 1 50%'
      }
    },
  },
  plugins: [
  ],
}
export default config
