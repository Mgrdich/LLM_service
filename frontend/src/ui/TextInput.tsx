import { ComponentProps } from "react";

interface TextInputProps extends ComponentProps<"textarea"> {}

function TextInput({ ...rest }: TextInputProps) {
  return <textarea {...rest} />;
}

export default TextInput;
