import { useMutation } from "@tanstack/react-query";
import useApi from "hooks/useApi.ts";
import { LogoutPath } from "./constants.ts";

export default function useLogoutCall() {
  const callApi = useApi();

  return useMutation({
    mutationFn: () =>
      callApi({
        method: "POST",
        url: LogoutPath,
        responseAs: "text",
      }),
  });
}
