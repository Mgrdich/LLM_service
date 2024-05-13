import { ComponentProps, forwardRef } from "react";
import { clsx } from "clsx";
import Input from "./Input.tsx";

interface InputWithLabelProps extends ComponentProps<"input"> {
  label: string;
  type?: "text" | "password";
  labelColor?: "gray" | "white";
}

const InputWithLabel = forwardRef<HTMLInputElement, InputWithLabelProps>(
  ({ labelColor = "gray", type = "text", label, ...rest }, ref) => (
    <label
      htmlFor={label}
      className={clsx(
        { "text-gray-700": labelColor === "gray", "text-white": labelColor === "white" },
        "form-label inline-block mb-2 w-full",
      )}
    >
      {label}
      <Input id={rest.name} type={type} {...rest} ref={ref} />
    </label>
  ),
);

InputWithLabel.displayName = "InputWithLabel";

export default InputWithLabel;
