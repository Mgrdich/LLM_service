import { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

export interface ButtonProps extends ComponentProps<"button"> {}

function Button({ className, ...rest }: ButtonProps) {
  return (
    <button
      className={twMerge(
        "bg-blue-500 p-2 hover:bg-blue-300 disabled:bg-blue-200 disabled:cursor-not-allowed",
        className,
      )}
      type="button"
      {...rest}
    />
  );
}

export default Button;
