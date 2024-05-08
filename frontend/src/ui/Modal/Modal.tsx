import { PropsWithChildren } from "react";
import { clsx } from "clsx";

interface FrameProps extends PropsWithChildren {
  open?: boolean;
  onClose?: () => void;
  title: string;
}

export default function Modal({ open, onClose, title, children }: FrameProps) {
  return (
    <div
      className={clsx(
        "fixed inset-0 z-10 p-8 text-white bg-gray-600/90",
        `${open ? "block" : "hidden"}`, // control visibility via `open` attribute (or render conditionally)
      )}
    >
      <div className="relative w-full max-w-sm mx-auto mt-8">
        <button
          type="button"
          className="absolute right-0 flex justify-center
          h-8 w-8 bg-gray-600 cursor-pointer shadow-xl"
          onClick={onClose}
        >
          <span className="text-2xl leading-7 select-none">&times;</span>
        </button>
        <div className="overflow-hidden bg-gray-800 rounded shadow-xl">
          <div className="block p-4 bg-gray-900">
            <h1 className="text-lg">{title}</h1>
          </div>
          <div className="p-4">{children}</div>
        </div>
      </div>
    </div>
  );
}
