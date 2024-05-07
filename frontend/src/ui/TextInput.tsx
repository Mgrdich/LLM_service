import { ComponentProps } from "react";

interface TextInputProps extends ComponentProps<"textarea"> {}

function TextInput(props: TextInputProps) {
  return <textarea {...props} />;
}

export default TextInput;
