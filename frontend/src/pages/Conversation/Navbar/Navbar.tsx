import useGetConversations from "hooks/api/useGetConversations.ts";
import SpinLoader from "ui/SpinLoader.tsx";
import Button from "ui/Button.tsx";
import { clsx } from "clsx";
import { ConversationId } from "models/Id.ts";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import EditConversationModal from "./EditConversationModal.tsx";

interface NavbarProps {
  id: ConversationId;
}

function Navbar({ id }: NavbarProps) {
  const { data, isFetching, isError } = useGetConversations();
  const navigate = useNavigate();
  const [modalConversationId, setModalConversationId] = useState("");

  const onConversationClick = (conversationId: ConversationId) => {
    navigate(`/conversation/${conversationId}`);
  };

  return (
    <div className="bg-neutral-50 p-2 h-full flex flex-col items-center dark:bg-neutral-900">
      <Button className="my-4 w-full bg-blue-500 text-white" onClick={() => navigate("/conversation")}>
        New Chat
      </Button>
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
    </div>
  );
}

export default Navbar;
