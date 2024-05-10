import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Welcome from "pages/Welcome/Welcome.tsx";
import Conversation from "pages/Conversation/Conversation.tsx";
import Login from "pages/Auth/Login.tsx";
import Register from "pages/Auth/Register.tsx";
import ForgotPassword from "pages/Auth/ForgotPassword.tsx";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import ConversationRedirect from "./pages/Conversation/ConversationRedirect.tsx";
import Error500 from "./pages/Error/Error500.tsx";
import Error404 from "./pages/Error/Error404.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    Component: Welcome,
  },
  {
    path: "/conversation",
    Component: ConversationRedirect,
  },
  {
    path: "/conversation/:id",
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
  {
    path: "/404",
    Component: Error404,
  },
  {
    path: "/500",
    Component: Error500,
  },
  {
    path: "*",
    Component: Error404,
  },
]);

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}

export default App;
