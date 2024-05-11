import { createContext, PropsWithChildren, useContext, useEffect, useMemo, useState } from "react";
import { useQueryClient } from "@tanstack/react-query";

const AuthContext = createContext<{ token: string | null; setToken:(token: string | null) => void }>({
  token: null,
  setToken: () => {},
});

export function AuthProvider({ children }: PropsWithChildren) {
  // State to hold the authentication token
  const [token, setToken_] = useState(localStorage.getItem("token"));
  const queryClient = useQueryClient();

  // Function to set the authentication token
  const setToken = (newToken: string | null) => {
    setToken_(newToken);
  };

  useEffect(() => {
    if (token) {
      localStorage.setItem("token", token);
    } else {
      localStorage.removeItem("token");
      queryClient.clear();
    }
  }, [queryClient, token]);

  // Memoized value of the authentication context
  const contextValue = useMemo(
    () => ({
      token,
      setToken,
    }),
    [token],
  );

  // Provide the authentication context to the children components
  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext);
