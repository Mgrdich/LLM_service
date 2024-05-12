import { useMutation } from "@tanstack/react-query";
import useApi from "hooks/useApi.ts";
import { Conversation } from "models/Conversation.ts";
import { ConversationStartPath } from "./constants.ts";

type OnSuccessCallback = (conversation: Conversation) => void;

export default function useStartConversation(onSuccessCallback: OnSuccessCallback) {
  const callApi = useApi();

  return useMutation<Conversation>({
    mutationFn: async () => {
      const conversation = await callApi<Conversation>({
        url: ConversationStartPath,
        method: "POST",
      });
      // TODO add zod validation
      return conversation;
    },
    onSuccess: (conversation) => {
      onSuccessCallback(conversation);
    },
  });
}
