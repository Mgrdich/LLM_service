import { PromptRole, PromptRoleType } from "models/Discussion.ts";
import { clsx } from "clsx";
import AnimateText from "ui/AnimateText.tsx";

interface DiscussionProps {
  role: PromptRoleType;
  text: string;
  animate?: boolean;
}

export default function Discussion({ role, text, animate }: DiscussionProps) {
  return (
    <div className="flex flex-col text-white">
      <div
        className={clsx(
          {
            "text-blue-600": role === PromptRole.Assistant,
            "text-green-600": role === PromptRole.User,
          },
          "font-bold",
        )}
      >
        {role === PromptRole.User ? "YOU" : "Chatto"}
      </div>
      {animate ? <AnimateText text={text} speed={10} /> : <div>{text}</div>}
    </div>
  );
}
