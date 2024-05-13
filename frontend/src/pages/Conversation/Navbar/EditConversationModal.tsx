import Modal from "ui/Modal/Modal.tsx";
import InputWithLabel from "ui/InputWithLabel.tsx";
import Button from "ui/Button.tsx";
import { useState } from "react";
import useEditConversation from "hooks/api/useEditConversation.ts";
import { ConversationCompact } from "models/Conversation.ts";

interface EditModalProps {
  conversation: ConversationCompact;
  unSet: () => void;
}

function EditConversationModal({ conversation, unSet }: EditModalProps) {
  const [title, setTitle] = useState(conversation?.title || "");
  const { mutateAsync } = useEditConversation();

  const onSubmit = async () => {
    await mutateAsync({ title, id: conversation.id });
    unSet();
  };

  return (
    <Modal open={!!conversation} title="Edit the Converstaion title" onClose={unSet}>
      <InputWithLabel
        labelColor="white"
        label="New title"
        value={title}
        defaultValue={conversation?.title}
        onInput={(e) => setTitle(e.currentTarget.value)}
      />

      <Button disabled={!title || conversation?.title === title} onClick={onSubmit}>
        Submit
      </Button>
    </Modal>
  );
}

export default EditConversationModal;
