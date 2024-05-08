import { ComponentProps } from "react";
import { twMerge } from "tailwind-merge";

interface TextInputProps extends ComponentProps<"textarea"> {}

function TextInput({ className, ...rest }: TextInputProps) {
  return <textarea {...rest} className={twMerge("p-2", className)} />;
}

export default TextInput;
