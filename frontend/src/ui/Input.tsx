import { ComponentProps, forwardRef } from "react";
import { twMerge } from "tailwind-merge";

interface InputProps extends Omit<ComponentProps<"input">, "type"> {
  type: "text" | "password";
}

const Input = forwardRef<HTMLInputElement, InputProps>(({ type = "text", className, ...rest }, ref) => (
  <input
    {...rest}
    type={type}
    ref={ref}
    className={twMerge(
      "form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding" +
        " border border-solid border-gray-300 rounded transition ease-in-out m-0 " +
        "focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none",
      className,
    )}
  />
));

Input.displayName = "Input";

export default Input;
