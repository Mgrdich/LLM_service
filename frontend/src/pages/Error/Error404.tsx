import Error from "./Error.tsx";

export default function Error404() {
  return (
    <div className="mb-12 ml-12 lg:mb-0 flex flex-col justify-center items-center h-screen pb-20 pt-20">
      <img src="/error404.png" className="w-fit rounded-lg" alt="" width="300" height="300" />
      <Error>Page Not Found</Error>
    </div>
  );
}
