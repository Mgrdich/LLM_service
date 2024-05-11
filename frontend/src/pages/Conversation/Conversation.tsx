import Grid from "ui/Grid/Grid.tsx";
import GridItem from "ui/Grid/GridItem.tsx";
import { useParams } from "react-router-dom";
import Navbar from "./Navbar/Navbar.tsx";
import Discussions from "./Discussions/Discussions.tsx";
import Prompt from "./Prompt/Prompt.tsx";

function Conversation() {
  const { id } = useParams();

  if (!id) return null;

  return (
    <Grid
      className="h-screen bg-neutral-50 dark:bg-neutral-900"
      gridTemplateColumns="20% 80%"
      gridTemplateRows="90.30% 9.70%"
    >
      <GridItem gridColumn="1" gridRow="1/3" className="bg-neutral-900 ml-2 mr-2">
        <Navbar id={id} />
      </GridItem>
      <GridItem gridColumn="2" gridRow="1" className="bg-neutral-800 overflow-auto">
        <Discussions id={id} />
      </GridItem>
      <GridItem gridColumn="2" gridRow="2" className="bg-neutral-800">
        <Prompt id={id} />
      </GridItem>
    </Grid>
  );
}

export default Conversation;
