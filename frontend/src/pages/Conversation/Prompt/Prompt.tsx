import TextInput from "../../../ui/TextInput.tsx";
import Button from "../../../ui/Button.tsx";

function Prompt() {
  return (
    <div className="p-4 flex">
      <TextInput className="flex-1" />
      <Button>Submit</Button>
    </div>
  );
}

export default Prompt;
