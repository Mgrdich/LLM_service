import Modal from "ui/Modal/Modal.tsx";
import InputWithLabel from "ui/InputWithLabel.tsx";
import { ConversationId } from "models/Id.ts";
import Button from "ui/Button.tsx";
import { useState } from "react";
import useEditConversation from "hooks/api/useEditConversation.ts";

interface EditModalProps {
  id: ConversationId;
  unSetId: () => void;
}

function EditConversationModal({ id, unSetId }: EditModalProps) {
  const [title, setTitle] = useState("");
  const { mutateAsync } = useEditConversation();

  const onSubmit = async () => {
    await mutateAsync({ title, id });
    unSetId();
  };

  return (
    <Modal open={!!id} title="Edit the Converstaion title" onClose={unSetId}>
      <InputWithLabel label="New title" value={title} onInput={(e) => setTitle(e.currentTarget.value)} />

      <Button disabled={!title} onClick={onSubmit}>
        Submit
      </Button>
    </Modal>
  );
}

export default EditConversationModal;
