import { ComponentProps, forwardRef } from "react";
import { twMerge } from "tailwind-merge";

interface CheckboxProps extends Omit<ComponentProps<"input">, "type"> {}

const Checkbox = forwardRef<HTMLInputElement, CheckboxProps>(({ className, ...rest }, ref) => (
  <input
    {...rest}
    ref={ref}
    type="checkbox"
    className={twMerge(
      "form-check-input appearance-none h-4 w-4 border border-gray-300" +
        "rounded-sm bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none" +
        " transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer",
      className,
    )}
  />
));

Checkbox.displayName = "Checkbox";

export default Checkbox;
