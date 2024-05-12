import { ComponentProps } from "react";
import { clsx } from "clsx";
import Input from "./Input.tsx";

interface InputWithLabelProps extends ComponentProps<"input"> {
  label: string;
  type?: "text" | "password";
  labelColor?: "gray" | "white";
}

function InputWithLabel({ labelColor = "gray", type = "text", label, name, ...rest }: InputWithLabelProps) {
  return (
    <label
      htmlFor={label}
      className={clsx(
        { "text-gray-700": labelColor === "gray", "text-white": labelColor === "white" },
        "form-label inline-block mb-2 w-full",
      )}
    >
      {label}
      <Input id={name} type={type} name={name} {...rest} />
    </label>
  );
}

export default InputWithLabel;
