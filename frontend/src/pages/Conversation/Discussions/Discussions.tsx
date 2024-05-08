import Discussion from "./Discussion.tsx";

function Discussions() {
  return Array.from([1, 2, 3, 4, 5]).map((i) => <Discussion key={i} />);
}

export default Discussions;
