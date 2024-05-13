import { PropsWithChildren } from "react";
import SpinLoader from "./SpinLoader.tsx";

interface FormSubmitButtonProps extends PropsWithChildren {
  disabled?: boolean;
  isLoading?: boolean;
}

function FormSubmitButton({ children, isLoading, disabled }: FormSubmitButtonProps) {
  return (
    <button
      type="submit"
      disabled={disabled}
      className="w-full flex gap-2 justify-center items-center px-6 py-2.5 bg-blue-600 text-white font-medium text-xs
      leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700
      focus:shadow-lg focus:outline-none focus:ring-0
      active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out
      disabled:bg-blue-200
      "
    >
      {isLoading && <SpinLoader size="xs" />}
      {children}
    </button>
  );
}

export default FormSubmitButton;
