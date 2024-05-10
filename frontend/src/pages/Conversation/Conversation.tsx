import Grid from "ui/Grid/Grid.tsx";
import GridItem from "ui/Grid/GridItem.tsx";
import { useParams } from "react-router-dom";
import Navbar from "./Navbar/Navbar.tsx";
import Discussions from "./Discussions/Discussions.tsx";
import Prompt from "./Prompt/Prompt.tsx";

// TODO this might need an id in the future
function Conversation() {
  const { id } = useParams();

  if (!id) return null;

  return (
    <Grid className="h-full" gridTemplateColumns="0.15fr 0.85fr" gridTemplateRows="85% 15%">
      <GridItem gridColumn="1/2" gridRow="1/3" className="bg-amber-900">
        <Navbar id={id} />
      </GridItem>
      <GridItem gridColumn="2" gridRow="1/2" className="bg-amber-400 overflow-auto">
        <Discussions id={id} />
      </GridItem>
      <GridItem gridColumn="2" gridRow="2" className="bg-warmGray-400">
        <Prompt id={id} />
      </GridItem>
    </Grid>
  );
}

export default Conversation;
