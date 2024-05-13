import { useMutation } from "@tanstack/react-query";
import toast from "react-hot-toast";
import useAuth from "context/useAuth.ts";
import useApi from "hooks/useApi.ts";
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
      console.log(err);
      // TODO to be integrated with BE errors messages
      toast.error("Bad credentials");
    },
  });
}
