import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import { useState } from "react";
import useLogin from "hooks/useLogin.ts";
import { useNavigate } from "react-router-dom";
import Button from "ui/Button.tsx";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { mutateAsync, isPending } = useLogin();
  const navigate = useNavigate();

  const onSubmit = async () => {
    await mutateAsync({ username, password });
    navigate("/conversation");
  };

  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Welcome Back :)
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
        <div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Username"
              name="username"
              type="text"
              placeholder="Enter Username"
              onInput={(e) => setUsername(e.currentTarget.value)}
            />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Password"
              type="password"
              name="password"
              placeholder="Enter Password"
              onInput={(e) => setPassword(e.currentTarget.value)}
            />
          </div>
          <div className="flex justify-between items-center mb-6" />
          <Button disabled={isPending} className="w-full" onClick={onSubmit}>
            Sign In
          </Button>
          <div className="text-gray-800 mt-6 text-center">
            Not a member?
            <LinkText to="/register">Register</LinkText>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
