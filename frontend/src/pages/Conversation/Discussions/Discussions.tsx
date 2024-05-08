import Discussion from "./Discussion.tsx";

const discussions = [
  {
    id: 1,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 2,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 3,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 4,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 5,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 6,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 7,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 8,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 9,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 10,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 11,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 12,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 13,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 15,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 16,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 17,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 18,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 19,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 20,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 21,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 22,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 23,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 24,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 25,
    role: "assistant",
    text: "i don't know you figure it out",
  },
  {
    id: 26,
    role: "user",
    text: "what is the biggest place on earth",
  },
  {
    id: 27,
    role: "assistant",
    text: "i don't know you figure it out",
  },
] as const;

export default function Discussions() {
  return (
    <div className="p-4 flex flex-col gap-6">
      {discussions.map((discussion) => (
        <Discussion key={discussion.id} text={discussion.text} role={discussion.role} />
      ))}
    </div>
  );
}
