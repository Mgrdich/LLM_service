import Button from "ui/Button.tsx";
import TextInput from "ui/TextInput.tsx";
import { useState } from "react";
import { ConversationId } from "models/Id.ts";
import useContinueConversation from "hooks/api/useContinueConversation.ts";
import SpinLoader from "ui/SpinLoader.tsx";

interface PromptProps {
  id: ConversationId;
}

function Prompt({ id }: PromptProps) {
  const { mutate, isPending } = useContinueConversation(id);
  const [textValue, setTextValue] = useState("");

  const submitQuestion = async () => {
    setTextValue("");
    mutate(textValue);
  };

  return (
    <div className="p-4 flex h-full gap-2">
      <TextInput
        className="flex-1 h-full resize-none"
        value={textValue}
        onInput={(e) => setTextValue(e.currentTarget.value)}
        disabled={isPending}
      />
      <Button
        disabled={isPending || !textValue}
        onClick={submitQuestion}
        className="flex items-center justify-center w-40 gap-2"
      >
        {isPending && <SpinLoader size="xs" />}
        Submit
      </Button>
    </div>
  );
}

export default Prompt;
