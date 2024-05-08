import { ComponentProps } from "react";
import Input from "./Input.tsx";

interface InputWithLabelProps extends ComponentProps<"input"> {
  label: string;
}

function InputWithLabel({ label, name, ...rest }: InputWithLabelProps) {
  return (
    <label htmlFor={label} className="form-label inline-block mb-2 text-gray-700 w-full">
      {label}
      <Input id={name} name={name} {...rest} />
    </label>
  );
}

export default InputWithLabel;
