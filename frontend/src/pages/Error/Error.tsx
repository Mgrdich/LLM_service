import LinkButton from "ui/LinkButton.tsx";
import { PropsWithChildren } from "react";

export default function Error({ children }: PropsWithChildren) {
  return (
    <div className="w-full h-full flex flex-col justify-center items-center gap-3.5">
      <span className="text-5xl font-bold tracking-tight md:text-5xl xl:text-5xl">{children}</span>
      <LinkButton to="/">Go To Home</LinkButton>
    </div>
  );
}
