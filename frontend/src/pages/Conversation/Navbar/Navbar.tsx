import Button from "ui/Button.tsx";
import { ConversationId } from "models/Id.ts";
import { useNavigate } from "react-router-dom";
import useGetUser from "hooks/api/useGetUser.ts";
import { Roles } from "models/User.ts";
import useLogout from "hooks/useLogout.ts";
import Conversations from "./Conversations.tsx";

interface NavbarProps {
  id: ConversationId;
}

function Navbar({ id }: NavbarProps) {
  const navigate = useNavigate();
  const { data } = useGetUser();
  const logout = useLogout();

  return (
    <div className="bg-neutral-50 p-2 h-full flex flex-col items-center dark:bg-neutral-900">
      <Button className="my-4 w-full" onClick={() => navigate("/conversation")}>
        New Chat
      </Button>
      {data?.role === Roles.PAID && <Conversations id={id} />}
      <Button onClick={logout} className="my-4 w-full bg-red-600 hover:bg-red-800">
        Logout
      </Button>
    </div>
  );
}

export default Navbar;
