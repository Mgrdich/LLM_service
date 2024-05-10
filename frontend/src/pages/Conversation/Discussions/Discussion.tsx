import { PromptRole, PromptRoleType } from "models/Discussion.ts";

interface DiscussionProps {
  role: PromptRoleType;
  text: string;
}

export default function Discussion({ role, text }: DiscussionProps) {
  return (
    <div className="flex flex-col">
      <div className="font-bold">{role === PromptRole.User ? "YOU" : "Chatto"}</div>
      <div>{text}</div>
    </div>
  );
}
