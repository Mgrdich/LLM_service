import { ComponentProps, PropsWithChildren } from "react";
import { twMerge } from "tailwind-merge";
import { Link } from "react-router-dom";

type ATagProps = ComponentProps<"a">;

interface LinkProps extends PropsWithChildren {
  className?: ATagProps["className"];
  to: string;
}

export default function LinkText({ className = "", to, children }: LinkProps) {
  return (
    <Link
      to={to}
      className={twMerge(
        "text-blue-600 hover:text-blue-700 focus:text-blue-700 " +
          "transition duration-200 ease-in-out ml-2 hover:underline",
        className,
      )}
    >
      {children}
    </Link>
  );
}
