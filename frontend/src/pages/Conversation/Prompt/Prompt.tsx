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
    <div className="ml-12 mr-12 h-full gap-2 flex items-center justify-center bg-neutral-50 dark:bg-neutral-800 ">
      <TextInput
        className="flex-1 h-full resize-none bg-transparent text-white "
        value={textValue}
        onInput={(e) => setTextValue(e.currentTarget.value)}
        disabled={isPending}
        placeholder="Enter your message..."
      />
      <Button
        disabled={isPending || !textValue}
        onClick={submitQuestion}
        className="w-20 bg-blue-500 text-white rounded-full flex gap-2 items-center justify-center"
      >
        {isPending && <SpinLoader size="xs" />}
        Submit
      </Button>
    </div>
  );
}

export default Prompt;
