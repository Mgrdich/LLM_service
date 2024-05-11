import { useCallback } from "react";
import { api, ApiRequest } from "utils/api.ts";
import useAuth from "context/useAuth.ts";

type CallApiArgs = Omit<ApiRequest, "token">;

export default function useApi() {
  const { token } = useAuth();

  return useCallback(async <TData>(args: CallApiArgs) => api<TData>({ ...args, token }), [token]);
}
