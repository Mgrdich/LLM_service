import { ConversationId } from "models/Id.ts";

export const Queries = {
  Conversation: "Conversation",
  User: "User",
} as const;

const basePath = import.meta.env.VITE_APP_BASE_URL;

// Conversation
export const ConversationsPath = `${basePath}/conversation`;
export const getConversationPath = (id: ConversationId) => `${basePath}/conversation/${id}`;
export const getContinueConversationPath = (id: ConversationId) => `${getConversationPath(id)}/continue`;

// User
export const UserPath = `${basePath}/me`;
