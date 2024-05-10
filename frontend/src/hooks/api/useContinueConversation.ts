import { useMutation, useQueryClient } from "@tanstack/react-query";
import useApi from "hooks/useApi.ts";
import { ConversationId } from "models/Id.ts";
import { Conversation } from "models/Conversation.ts";
import { Discussion, PromptRole } from "models/Discussion.ts";
import { getContinueConversationPath, Queries } from "./constants.ts";

// TODO need to check the context switch case
export default function useContinueConversation(id: ConversationId) {
  const callApi = useApi();
  const queryClient = useQueryClient();
  const tempId = "tempId";

  return useMutation({
    mutationFn: async (text: string) => {
      const discussions = await callApi<Discussion[]>({
        url: getContinueConversationPath(id),
        method: "PUT",
        body: {
          text,
        },
      });

      return discussions;
    },
    onMutate: async (variables) => {
      const queryKey = [Queries.Conversation, id];
      queryClient.setQueryData(queryKey, (old: Conversation) => {
        const newData = {
          ...old,
        };
        newData.discussions = [
          ...old.discussions,
          {
            id: tempId,
            text: variables,
            promptRole: PromptRole.User,
            lastUpdatedOn: new Date().toDateString(),
            createdOn: new Date().toDateString(),
          },
        ];

        return newData;
      });
    },
    onSuccess: async (data) => {
      const queryKey = [Queries.Conversation, id];
      queryClient.setQueryData(queryKey, (old: Conversation) => {
        const newData = {
          ...old,
        };
        newData.discussions = old.discussions.filter((item) => item.id !== tempId);

        data.forEach((item) => {
          newData.discussions.push({
            ...item,
          });
        });

        return newData;
      });
    },
  });
}
