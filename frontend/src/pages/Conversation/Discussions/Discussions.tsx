import { ConversationId } from "models/Id.ts";
import useGetConversation from "hooks/api/useGetConversation.ts";
import SpinLoader from "ui/SpinLoader.tsx";
import Discussion from "./Discussion.tsx";

interface DiscussionProps {
  id: ConversationId;
}

export default function Discussions({ id }: DiscussionProps) {
  const { data, isLoading, isError } = useGetConversation(id);

  return (
    <div className="p-4 flex flex-col gap-6 bg-neutral-50 dark:bg-neutral-800">
      {isLoading && !data && <SpinLoader size="l" />}
      {isError && <div className="text-3xl text-red-600 text-center">Something Went Wrong</div>}
      {data?.discussions?.length === 0 && !isError && !isLoading && (
        <div className="text-3xl text-white text-center">Ask me Questions</div>
      )}
      {data?.discussions?.map((discussion) => (
        <Discussion key={discussion.id} text={discussion.text} role={discussion.promptRole} />
      ))}
    </div>
  );
}
