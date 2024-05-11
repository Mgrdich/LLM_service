import { useMutation } from "@tanstack/react-query";
import { RolesType } from "models/User.ts";
import useApi from "./useApi.ts";
import { RegisterPath } from "./api/constants.ts";

interface RegisterRequest {
  username: string;
  firstName: string;
  lastName: string;
  role: RolesType;
  password: string;
}

export default function useRegister() {
  const callApi = useApi();
  return useMutation({
    mutationFn: ({ username, password, firstName, lastName, role }: RegisterRequest) =>
      callApi<{ name: string }>({
        url: RegisterPath,
        body: { username, password, firstName, lastName, role },
        method: "POST",
      }),
  });
}
