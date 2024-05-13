import { useState } from "react";
import { clsx } from "clsx";
import SpinLoader from "ui/SpinLoader.tsx";
import Button from "ui/Button.tsx";
import useGetConversations from "hooks/api/useGetConversations.ts";
import { ConversationId } from "models/Id.ts";
import { useNavigate } from "react-router-dom";
import useDeleteConversation from "hooks/api/useDeleteConversation.ts";
import { ConversationCompact } from "models/Conversation.ts";
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
  const { mutateAsync } = useDeleteConversation();
  const [modalConversation, setModalConversation] = useState<ConversationCompact | null>(null);

  const onDelete = async (conversationId: ConversationId) => {
    await mutateAsync(conversationId);
    const index = data?.findIndex((item) => item.id);

    if (!index) return;

    // TODO this needs to be tested

    if (data?.[index - 1]) {
      navigate(`/conversation/${data?.[index - 1].id}`);
      return;
    }

    if (data?.[index + 1]) {
      navigate(`/conversation/${data?.[index + 1].id}`);
    }

    navigate("/conversation");
  };

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
          <div
            role="button"
            className={clsx(
              "truncate border-gray-600 hover:border-blue-600 border-2 p-2 ",
              "text-white flex justify-between items-center w-full min-h-6 cursor-pointer",
              {
                "border-white": conversation.id === id,
              },
            )}
            tabIndex={0}
            onKeyDown={() => onConversationClick(conversation.id)}
            key={conversation.id}
            onClick={() => onConversationClick(conversation.id)}
          >
            <div className="w-full truncate text-left">{conversation.title || "Untitled"}</div>
            <div>
              <Button
                onClick={(e) => {
                  e.stopPropagation();
                  setModalConversation(conversation);
                }}
                className="bg-transparent hover:bg-transparent hover:text-blue-400 p-2"
              >
                <span className="text-blue-400 hover:text-blue-900">Edit</span>
              </Button>
              <Button
                onClick={(e) => {
                  e.stopPropagation();
                  onDelete(conversation.id);
                }}
                className="bg-transparent hover:bg-transparent hover:text-red-400 p-2"
              >
                <span className="text-red-400 hover:text-red-800">Delete</span>
              </Button>
            </div>
          </div>
        ))}
      </div>
      {modalConversation && (
        <EditConversationModal conversation={modalConversation} unSet={() => setModalConversation(null)} />
      )}
    </>
  );
}

export default Conversations;
