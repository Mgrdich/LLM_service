import { useMutation, useQueryClient } from "@tanstack/react-query";
import useApi from "hooks/useApi.ts";
import { ConversationId } from "models/Id.ts";
import { Conversation } from "models/Conversation.ts";
import { Discussion, PromptRole } from "models/Discussion.ts";
import toast from "react-hot-toast";
import { getContinueConversationPath, Queries } from "./constants.ts";

// TODO need to check the context switch case
export default function useContinueConversation() {
  const callApi = useApi();
  const queryClient = useQueryClient();
  const tempId = "tempId";

  return useMutation<Discussion[], void, { text: string; id: ConversationId }>({
    mutationFn: async ({ text, id }) => {
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
      const queryKey = [Queries.Conversation, variables.id];
      queryClient.setQueryData(queryKey, (old: Conversation) => {
        const newData = {
          ...old,
        };
        newData.discussions = [
          ...old.discussions,
          {
            id: tempId,
            text: variables.text,
            promptRole: PromptRole.User,
            lastUpdatedOn: new Date().toDateString(),
            createdOn: new Date().toDateString(),
          },
        ];

        return newData;
      });
    },
    onSuccess: async (data, variables) => {
      const queryKey = [Queries.Conversation, variables.id];
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
    onError: () => {
      // TODO integrate with BE error message
      toast.error("Modal could not answer your question");
    },
  });
}
