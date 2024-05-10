import { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";
import { clsx } from "clsx";

interface TextInputProps extends ComponentProps<"textarea"> {}

function TextInput({ className, ...rest }: TextInputProps) {
  return (
    <textarea
      {...rest}
      className={clsx("disabled:bg-gray-100 disabled:cursor-not-allowed", twMerge("p-2", className))}
    />
  );
}

export default TextInput;
