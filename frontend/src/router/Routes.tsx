import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { useMemo } from "react";
import Login from "pages/Auth/Login.tsx";
import Register from "pages/Auth/Register.tsx";
import Error404 from "pages/Error/Error404.tsx";
import Error500 from "pages/Error/Error500.tsx";
import Conversation from "pages/Conversation/Conversation.tsx";
import useAuth from "context/useAuth.ts";
import Welcome from "pages/Welcome/Welcome.tsx";
import ConversationRedirect from "pages/Conversation/ConversationRedirect.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";

const routesForPublic = [
  {
    path: "/",
    Component: Welcome,
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
];

const routesForProtected = [
  {
    path: "/",
    Component: ProtectedRoute,
    children: [
      {
        path: "/conversation",
        Component: ConversationRedirect,
      },
      {
        path: "/conversation/:id",
        Component: Conversation,
      },
    ],
  },
];

const routesForNotAuthenticatedOnly = [
  {
    path: "/login",
    Component: Login,
  },
  {
    path: "/register",
    Component: Register,
  },
  // {
  //   path: "/forgot-password",
  //   Component: ForgotPassword,
  // },
];

export default function Routes() {
  const { token } = useAuth();

  const router = useMemo(() => {
    const routes = [...routesForPublic, ...(!token ? routesForNotAuthenticatedOnly : []), ...routesForProtected];
    return createBrowserRouter(routes);
  }, [token]);

  // Provide the router configuration using RouterProvider
  return <RouterProvider router={router} />;
}
