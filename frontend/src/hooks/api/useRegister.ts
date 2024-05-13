import { useMutation } from "@tanstack/react-query";
import toast from "react-hot-toast";
import { RolesType } from "models/User.ts";
import useApi from "hooks/useApi.ts";
import { ApiError } from "models/Errors.ts";
import { RegisterPath } from "./constants.ts";

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
    onSuccess: () => {
      toast.success("User created successfully");
    },
    onError: (err) => {
      if (err instanceof ApiError) {
        toast.error(err.message);
        return;
      }

      toast.error("Something went wrong with user registration");
    },
  });
}
