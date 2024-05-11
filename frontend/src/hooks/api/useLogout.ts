import { useAuth } from "context/AuthContext.tsx";

export default function useLogout() {
  const { setToken } = useAuth();

  return () => {
    setToken(null);
  };
}
