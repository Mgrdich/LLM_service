import { useMutation } from "@tanstack/react-query";
import toast from "react-hot-toast";
import useAuth from "context/useAuth.ts";
import useApi from "hooks/useApi.ts";
import { ApiError } from "models/Errors.ts";
import { LoginPath } from "./constants.ts";

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
      toast.dismiss();
      setToken(res.token);
    },
    onError: (err) => {
      if (err instanceof ApiError) {
        toast.error(err.message);
        return;
      }

      toast.error("Something went wrong with the login");
    },
  });
}
