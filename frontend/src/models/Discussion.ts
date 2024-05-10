import { ObjectValues } from "utils/types/index.ts";
import { DiscussionId } from "./Id.ts";
import { Dates } from "./Dates.ts";

export const PromptRole = {
  User: "USER",
  Assistant: "ASSISTANT",
} as const;

export type PromptRoleType = ObjectValues<typeof PromptRole>;

export interface Discussion extends Dates {
  id: DiscussionId;
  text: string;
  promptRole: PromptRoleType;
}
