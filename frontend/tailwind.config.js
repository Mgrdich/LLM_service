import colors from "tailwindcss/colors.js";

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}",],
  theme: {
    extend: {
      colors: {
        ...colors
      },
      fontFamily: {
        'mono': ['Fira Code', 'monospace']
      }
    },
  },
  plugins: [],
}

