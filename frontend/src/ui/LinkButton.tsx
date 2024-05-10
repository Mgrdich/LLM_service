import { Link } from "react-router-dom";
import Button, { ButtonProps } from "./Button.tsx";

interface LinkButtonProps extends ButtonProps {
  to: string;
}

export default function LinkButton({ to, children, ...rest }: LinkButtonProps) {
  return (
    <Link to={to}>
      <Button {...rest}>{children}</Button>
    </Link>
  );
}
