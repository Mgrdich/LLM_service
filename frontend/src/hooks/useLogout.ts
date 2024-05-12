import useAuth from "context/useAuth.ts";
import useLogoutCall from "./api/useLogoutCall.ts";

export default function useLogout() {
  const { setToken } = useAuth();
  const { mutateAsync } = useLogoutCall();

  return async () => {
    await mutateAsync();
    setToken(null);
  };
}
