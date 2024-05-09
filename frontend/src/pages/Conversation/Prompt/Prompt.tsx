import Button from "ui/Button.tsx";
import TextInput from "ui/TextInput.tsx";

function Prompt() {
  return (
    <div className="p-4 flex h-full gap-2">
      <TextInput className="flex-1 h-full resize-none" />
      <Button>Submit</Button>
    </div>
  );
}

export default Prompt;
