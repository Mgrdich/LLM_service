interface DiscussionProps {
  role: "user" | "assistant"; // TODO transform it into object as const enum
  text: string;
}

export default function Discussion({ role, text }: DiscussionProps) {
  return (
    <div className="flex flex-col">
      <div className="font-bold">{role === "user" ? "YOU" : "Chatto"}</div>
      <div>{text}</div>
    </div>
  );
}
