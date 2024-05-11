import { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface InputProps extends Omit<ComponentProps<"input">, "type"> {
  type: "text" | "password";
}

export default function Input({ type = "text", className, ...rest }: InputProps) {
  return (
    <input
      {...rest}
      type={type}
      className={twMerge(
        "form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding" +
          " border border-solid border-gray-300 rounded transition ease-in-out m-0 " +
          "focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none",
        className,
      )}
    />
  );
}
