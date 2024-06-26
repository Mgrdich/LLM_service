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
  const { mutate, isPending } = useContinueConversation();
  const [textValue, setTextValue] = useState("");

  const submitQuestion = () => {
    setTextValue("");
    mutate({ text: textValue, id });
  };

  return (
    <div className="mx-12 h-full gap-2 flex items-center justify-center bg-neutral-50 dark:bg-neutral-800 p-4">
      <TextInput
        className="flex-1 h-full bg-transparent resize-none text-white max-w-[60%]"
        value={textValue}
        onInput={(e) => setTextValue(e.currentTarget.value)}
        disabled={isPending}
        placeholder="Enter your message..."
      />
      <Button
        disabled={isPending || !textValue}
        onClick={submitQuestion}
        className="w-30 bg-blue-500 text-white flex gap-2 items-center justify-center"
      >
        {isPending && <SpinLoader size="xs" />}
        Submit
      </Button>
    </div>
  );
}

export default Prompt;
