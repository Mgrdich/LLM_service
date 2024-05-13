import Modal from "ui/Modal/Modal.tsx";
import InputWithLabel from "ui/InputWithLabel.tsx";
import useEditConversation from "hooks/api/useEditConversation.ts";
import { ConversationCompact } from "models/Conversation.ts";
import { SubmitHandler, useForm } from "react-hook-form";
import ErrorLabel from "ui/ErrorLabel.tsx";
import FormSubmitButton from "ui/FormSubmitButton.tsx";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";

interface EditModalProps {
  conversation: ConversationCompact;
  unSet: () => void;
}

type EditTitleForm = {
  title: string;
};

const getEditSchema = (title: string) =>
  z.object({
    title: z
      .string()
      .min(3)
      .max(30)
      .refine((value) => value !== title, "it cannot be like the old one"),
  });

function EditConversationModal({ conversation, unSet }: EditModalProps) {
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting, isValid },
  } = useForm<EditTitleForm>({
    mode: "all",
    defaultValues: { title: conversation.title },
    resolver: zodResolver(getEditSchema(conversation.title)),
  });

  const { mutateAsync } = useEditConversation();

  const onSubmit: SubmitHandler<EditTitleForm> = async ({ title }) => {
    if (!isValid) return;

    await mutateAsync({ title, id: conversation.id });
    unSet();
  };

  return (
    <Modal open={!!conversation} title="Edit the Converstaion title" onClose={unSet}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <InputWithLabel labelColor="white" label="New title" {...register("title")} />
        <ErrorLabel error={errors.title} />
        <FormSubmitButton disabled={isSubmitting || !isValid}>Submit</FormSubmitButton>
      </form>
    </Modal>
  );
}

export default EditConversationModal;
