import Modal from "ui/Modal/Modal.tsx";
import InputWithLabel from "ui/InputWithLabel.tsx";
import { ConversationId } from "models/Id.ts";
import Button from "ui/Button.tsx";

interface EditModalProps {
  id: ConversationId;
  unSetId: () => void;
}

function EditConversationModal({ id, unSetId }: EditModalProps) {
  return (
    <Modal open={!!id} title="Edit the Converstaion title" onClose={unSetId}>
      <InputWithLabel label="New title" />

      <Button onClick={unSetId}>Submit</Button>
    </Modal>
  );
}

export default EditConversationModal;
