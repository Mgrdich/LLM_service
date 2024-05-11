import { useMutation } from "@tanstack/react-query";
import { RolesType } from "models/User.ts";
import useApi from "./useApi.ts";
import { LoginPath } from "./api/constants.ts";

interface RegisterRequest {
  username: string;
  firstname: string;
  lastname: string;
  role: RolesType;
  password: string;
}

export default function useRegister() {
  const callApi = useApi();
  return useMutation({
    mutationFn: ({ username, password, firstname, lastname, role }: RegisterRequest) =>
      callApi<{ name: string }>({
        url: LoginPath,
        body: { username, password, firstname, lastname, role },
        method: "POST",
      }),
  });
}
