import SpinLoader from "ui/SpinLoader.tsx";

function PageLoader() {
  return (
    <div className="w-full h-full flex justify-center items-center">
      <SpinLoader size="xl" />
    </div>
  );
}

export default PageLoader;
