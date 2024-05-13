import { PromptRole, PromptRoleType } from "models/Discussion.ts";
import { clsx } from "clsx";

interface DiscussionProps {
  role: PromptRoleType;
  text: string;
}

export default function Discussion({ role, text }: DiscussionProps) {
  return (
    <div className="flex flex-col text-white">
      <div
        className={clsx(
          { "text-blue-600": role === PromptRole.Assistant, "text-green-600": role === PromptRole.User },
          "font-bold",
        )}
      >
        {role === PromptRole.User ? "YOU" : "Chatto"}
      </div>
      <div>{text}</div>
    </div>
  );
}
