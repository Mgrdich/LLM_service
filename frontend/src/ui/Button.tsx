import { ComponentProps } from "react";

interface ButtonProps extends ComponentProps<"button"> {}

function Button(props: ButtonProps) {
  return <button type="button" {...props} />;
}

export default Button;
