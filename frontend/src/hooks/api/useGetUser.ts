import { useQuery } from "@tanstack/react-query";
import { User } from "models/User.ts";
import useApi from "hooks/useApi.ts";
import { Queries, UserPath } from "./constants.ts";

export default function useGetUser() {
  const callApi = useApi();

  return useQuery({
    queryKey: [Queries.User],
    queryFn: () => callApi<User>({ url: UserPath }),
    staleTime: Infinity,
  });
}
