import useAuth from "context/useAuth.ts";

export default function useLogout() {
  const { setToken } = useAuth();

  return () => {
    setToken(null);
  };
}
