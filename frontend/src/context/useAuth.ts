import { useContext } from "react";
import { AuthContext } from "./AuthContext.tsx";

export default function useAuth() {
  return useContext(AuthContext);
}
