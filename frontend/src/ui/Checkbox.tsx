import { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface CheckboxProps extends Omit<ComponentProps<"input">, "type"> {}

export default function Checkbox({ className, ...rest }: CheckboxProps) {
  return (
    <input
      {...rest}
      type="checkbox"
      className={twMerge(
        "form-check-input appearance-none h-4 w-4 border border-gray-300 rounded-sm bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 mt-1 align-top bg-no-repeat bg-center bg-contain float-left mr-2 cursor-pointer",
        className,
      )}
    />
  );
}
