import { useCallback } from "react";
import { api, ApiRequest } from "utils/api.ts";

type CallApiArgs = Omit<ApiRequest, "token">;

export default function useApi() {
  return useCallback(async <TData>(args: CallApiArgs) => api<TData>(args), []);
}
