import { useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import useStartConversation from "hooks/api/useStartConversation.ts";
import PageLoader from "pages/Loader/PageLoader.tsx";

function ConversationRedirect() {
  const navigate = useNavigate();
  const isCalledRef = useRef(false);

  const { mutate, isError, data } = useStartConversation((conversation) => {
    navigate(conversation.id);
  });

  useEffect(() => {
    if (!data && !isCalledRef.current) {
      isCalledRef.current = true;
      mutate();
    }
  }, [data, mutate]);

  useEffect(() => {
    if (isError) {
      navigate("/500");
    }
  }, [isError]);

  return <PageLoader />;
}

export default ConversationRedirect;
