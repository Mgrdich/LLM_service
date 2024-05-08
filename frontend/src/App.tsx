import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Welcome from "pages/Welcome/Welcome.tsx";
import Conversation from "pages/Conversation/Conversation.tsx";
import Login from "pages/Auth/Login.tsx";
import Register from "pages/Auth/Register.tsx";
import ForgotPassword from "pages/Auth/ForgotPassword.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    Component: Welcome,
  },
  {
    path: "/conversation",
    Component: Conversation,
  },
  {
    path: "/login",
    Component: Login,
  },
  {
    path: "/register",
    Component: Register,
  },
  {
    path: "/forgot-password",
    Component: ForgotPassword,
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
