import { ObjectValues } from "utils/types/index.ts";

export const Roles = {
  FREE: "FREE",
  PAID: "PAID",
} as const;

export type RolesType = ObjectValues<typeof Roles>;

export interface User {
  username: string;
  role: RolesType;
}
