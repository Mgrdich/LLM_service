import { FieldError } from "react-hook-form";

interface ErrorLabelProps {
  error: FieldError | undefined;
}

export default function ErrorLabel({ error }: ErrorLabelProps) {
  return error && <div className="text-red-600 text-xs">{error?.message}</div>;
}
