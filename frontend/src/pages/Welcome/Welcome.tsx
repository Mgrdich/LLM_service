import { Link } from "react-router-dom";
import useAuth from "context/useAuth.ts";

function Welcome() {
  const { token } = useAuth();

  return (
    <div className="bg-neutral-50 px-2 py-6 text-center dark:bg-neutral-900 md:px-12 lg:text-left min-h-screen">
      <div className="w-100 mx-auto sm:max-w-2xl md:max-w-3xl lg:max-w-5xl xl:max-w-7xl">
        <div className="grid items-center gap-12 lg:grid-cols-2">
          <div className="mt-12 lg:mt-0">
            <h1 className="mt-2 mb-16 text-5xl text-white font-bold tracking-tight md:text-6xl xl:text-7xl">
              Welcome to
              <br />
              <br />
              <span
                className="relative text-blue-500 w-[max-content] before:absolute before:inset-0
                           before:animate-typewriter before:bg-neutral-900 after:absolute after:inset-0
                           after:w-[0.125em] after:animate-caret after:bg-blue-500"
              >
                Chatto!
              </span>
            </h1>
            <Link
              className="mb-2 inline-block bg-blue-500 rounded bg-primary px-12 pt-4 pb-3.5 text-sm
              font-medium uppercase
              leading-normal text-white shadow-[0_4px_9px_-4px_#3b71ca] transition
              duration-150 ease-in-out hover:bg-primary-600
              hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]
              focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]
              focus:outline-none focus:ring-0 active:bg-primary-700
              active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]
              dark:shadow-[0_4px_9px_-4px_rgba(59,113,202,0.5)]
              dark:hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]
              dark:focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]
              dark:active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.2),0_4px_18px_0_rgba(59,113,202,0.1)]
              md:mr-2 md:mb-0"
              data-te-ripple-init=""
              data-te-ripple-color="light"
              to={token ? "/conversation" : "/login"}
              role="button"
            >
              Get started
            </Link>
          </div>
          <div className="mb-12 lg:mb-0">
            <img src="/chat-bot-bro.png" className="w-full rounded-lg" alt="" width="600" height="600" />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Welcome;
