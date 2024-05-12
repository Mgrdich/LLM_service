import { ConversationId } from "models/Id.ts";

export const Queries = {
  Conversation: "Conversation",
  User: "User",
} as const;

const basePath = `${import.meta.env.VITE_APP_BASE_URL}/api/v1`;

// Conversation
export const ConversationStartPath = `${basePath}/conversation`;
export const ConversationsPath = `${basePath}/paid/conversation`;
export const getConversationPath = (id: ConversationId) => `${basePath}/conversation/${id}`;
export const getContinueConversationPath = (id: ConversationId) => `${getConversationPath(id)}/continue`;

// User
export const UserPath = `${basePath}/me`;
export const LoginPath = `${basePath}/login`;
export const LogoutPath = `${basePath}/logout`;
export const RegisterPath = `${basePath}/register`;
