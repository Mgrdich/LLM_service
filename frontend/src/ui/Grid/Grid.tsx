import type { ComponentProps, CSSProperties, PropsWithChildren } from "react";
import { twMerge } from "tailwind-merge";

type GridApplicationCondProps =
  | {
      gridTemplateColumns?: CSSProperties["gridTemplateColumns"];
      gridTemplateRows?: CSSProperties["gridTemplateRows"];
      gridTemplate?: never;
    }
  | {
      gridTemplateColumns?: never;
      gridTemplateRows?: never;
      gridTemplate?: CSSProperties["gridTemplate"];
    };

type GridApplicationProps = GridApplicationCondProps &
  PropsWithChildren & {
    className?: ComponentProps<"div">["className"];
  };

export default function Grid({ children, className, ...props }: GridApplicationProps) {
  return (
    <div className={twMerge("grid w-full h-full bg-neutral-50 dark:bg-neutral-900", className)} style={props}>
      {children}
    </div>
  );
}
