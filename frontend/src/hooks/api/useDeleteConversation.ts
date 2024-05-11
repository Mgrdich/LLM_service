import { useMutation, useQueryClient } from "@tanstack/react-query";
import { ConversationCompact } from "models/Conversation.ts";
import useApi from "hooks/useApi.ts";
import { ConversationId } from "models/Id.ts";
import { getConversationPath, Queries } from "./constants.ts";

export default function useDeleteConversation() {
  const callApi = useApi();
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: async (id: ConversationId) => {
      await callApi<void>({
        url: getConversationPath(id),
        method: "DELETE",
      });
    },
    onSuccess: (_, id) => {
      const queryKey = [Queries.Conversation];
      queryClient.setQueryData(queryKey, (old: ConversationCompact[]) => old?.filter((item) => item.id === id));
    },
  });
}
