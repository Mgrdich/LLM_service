import { useMutation, useQueryClient } from "@tanstack/react-query";
import { ConversationCompact } from "models/Conversation.ts";
import useApi from "hooks/useApi.ts";
import { ConversationId } from "models/Id.ts";
import { getConversationPath, Queries } from "./constants.ts";

export default function useEditConversation() {
  const callApi = useApi();
  const queryClient = useQueryClient();

  return useMutation<ConversationCompact, unknown, { title: string; id: ConversationId }>({
    mutationFn: async ({ title, id }) => {
      const conversation = await callApi<ConversationCompact>({
        url: getConversationPath(id),
        method: "PUT",
        body: { title },
      });
      // TODO add zod validation
      return conversation;
    },
    onSuccess: (conversation) => {
      const queryKey = [Queries.Conversation];
      queryClient.setQueryData(queryKey, (old: ConversationCompact[]) => {
        old?.map((item) => {
          if (item.id === conversation.id) {
            return conversation;
          }
          return item;
        });
      });
    },
  });
}
