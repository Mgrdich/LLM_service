import { Discussion } from "./Discussion.ts";
import { ConversationId, DiscussionId } from "./Id.ts";
import { Dates } from "./Dates.ts";

export interface Conversation extends Dates {
  id: ConversationId;
  title: string;
  discussions: Discussion[];
}

export interface ConversationCompact extends Dates {
  id: ConversationId;
  title: string;
  discussions: DiscussionId[];
}
