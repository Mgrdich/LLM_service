import { useQuery } from "@tanstack/react-query";
import { Conversation } from "models/Conversation.ts";
import useApi from "hooks/useApi.ts";
import { ConversationId } from "models/Id.ts";
import { getConversationPath, Queries } from "./constants.ts";

export default function useGetConversation(id: ConversationId) {
  const callApi = useApi();

  return useQuery<Conversation>({
    queryKey: [Queries.Conversation, id],
    queryFn: async () => {
      const conversation = await callApi<Conversation>({ url: getConversationPath(id) });
      // TODO add zod validation
      return conversation;
    },
    staleTime: 5 * 60 * 60,
  });
}
