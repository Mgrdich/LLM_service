import type { PropsWithChildren, CSSProperties, ComponentProps } from "react";

type GridItemConditionalProps =
  | {
      gridColumn?: CSSProperties["gridColumn"];
      gridRow?: CSSProperties["gridRow"];
      gridArea?: never["gridArea"];
    }
  | {
      gridColumn?: never;
      gridRow?: never;
      gridArea?: CSSProperties["gridArea"];
    };

export type GridItemProps = GridItemConditionalProps &
  PropsWithChildren & { className?: ComponentProps<"div">["className"] };

export default function GridItem({ children, className, ...props }: GridItemProps) {
  return (
    <div style={{ ...props }} className={className}>
      {children}
    </div>
  );
}
