import { useMutation } from "@tanstack/react-query";
import useApi from "./useApi.ts";
import { LoginPath } from "./api/constants.ts";
import { useAuth } from "../context/AuthContext.tsx";

interface LoginRequest {
  username: string;
  password: string;
}

export default function useLogin() {
  const callApi = useApi();
  const { setToken } = useAuth();

  return useMutation({
    mutationFn: ({ username, password }: LoginRequest) =>
      callApi<{ token: string }>({ url: LoginPath, body: { username, password }, method: "POST" }),
    onSuccess: (res) => {
      setToken(res.token);
    },
  });
}
