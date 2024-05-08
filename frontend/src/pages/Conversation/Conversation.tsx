import Navbar from "./Navbar/Navbar.tsx";
import Discussions from "./Discussions/Discussions.tsx";
import Prompt from "./Prompt/Prompt.tsx";

// TODO this might need an id in the future
function Conversation() {
  return (
    <div className="grid grid-rows-2 grid-cols-2 h-full">
      <div className="row-span-2 w-40">
        <Navbar />
      </div>
      <div className="grid-flow-row">
        <Discussions />
      </div>
      <div className="h-8">
        <Prompt />
      </div>
    </div>
  );
}

export default Conversation;
