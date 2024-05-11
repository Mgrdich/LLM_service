import { useState } from "react";
import { clsx } from "clsx";
import SpinLoader from "ui/SpinLoader.tsx";
import Button from "ui/Button.tsx";
import useGetConversations from "hooks/api/useGetConversations.ts";
import { ConversationId } from "models/Id.ts";
import { useNavigate } from "react-router-dom";
import EditConversationModal from "./EditConversationModal.tsx";

interface ConversationProps {
  id: ConversationId;
}

function Conversations({ id }: ConversationProps) {
  const navigate = useNavigate();
  const onConversationClick = (conversationId: ConversationId) => {
    navigate(`/conversation/${conversationId}`);
  };

  const { data, isFetching, isError } = useGetConversations();
  const [modalConversationId, setModalConversationId] = useState("");
  return (
    <>
      <div
        className={clsx("flex gap-2 flex-col overflow-auto flex-1 w-full items-center", {
          "justify-center": isFetching || data?.length === 0,
        })}
      >
        {isError && <p className="text-red-600">Something Went Wrong</p>}
        {isFetching && <SpinLoader size="l" />}
        {!isFetching && data && data.length === 0 && <div>No Conversation</div>}
        {data?.map((conversation) => (
          <button
            type="button"
            className={clsx("truncate border-2 p-2 text-white flex justify-between items-center w-full min-h-8", {
              "border-gray-600": conversation.id === id,
            })}
            key={conversation.id}
            onClick={() => onConversationClick(conversation.id)}
          >
            {conversation.title || "Untitled"}
            <Button
              onClick={(e) => {
                e.stopPropagation();
                setModalConversationId(conversation.id);
              }}
              className="text-white"
            >
              Edit
            </Button>
          </button>
        ))}
      </div>
      <EditConversationModal id={modalConversationId} unSetId={() => setModalConversationId("")} />
    </>
  );
}

export default Conversations;
