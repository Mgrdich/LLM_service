import { ComponentProps, PropsWithChildren } from "react";
import { twMerge } from "tailwind-merge";

type ATagProps = ComponentProps<"a">;

interface LinkProps extends PropsWithChildren {
  className?: ATagProps["className"];
  href: ATagProps["href"];
}

export default function Link({ className = "", href, children }: LinkProps) {
  return (
    <a
      href={href}
      className={twMerge(
        "text-blue-600 hover:text-blue-700 focus:text-blue-700 " +
          "transition duration-200 ease-in-out ml-2 hover:underline",
        className,
      )}
    >
      {children}
    </a>
  );
}
