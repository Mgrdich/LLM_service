import { useEffect, useState } from "react";

interface AnimateTextProps {
  text: string;
  speed: number;
}

export default function AnimateText({ text, speed }: AnimateTextProps) {
  const [displayedText, setDisplayedText] = useState("");
  const [index, setIndex] = useState(0);

  useEffect(() => {
    if (index < text.length) {
      const timeout = setTimeout(() => {
        setDisplayedText(displayedText + text[index]);
        setIndex(index + 1);
      }, speed);

      return () => clearTimeout(timeout);
    }
  }, [index, text, speed, displayedText]);

  return <div className="typewriter">{displayedText}</div>;
}
