import { useQuery } from "@tanstack/react-query";
import { ConversationCompact } from "models/Conversation.ts";
import useApi from "hooks/useApi.ts";
import { ConversationsPath, Queries } from "./constants.ts";

export default function useGetConversations() {
  const callApi = useApi();

  return useQuery<ConversationCompact[]>({
    queryKey: [Queries.Conversation],
    queryFn: async () => {
      const conversation = await callApi<ConversationCompact[]>({
        url: ConversationsPath,
      });
      // TODO add zod validation
      return conversation;
    },
    staleTime: 5 * 60 * 60,
  });
}
