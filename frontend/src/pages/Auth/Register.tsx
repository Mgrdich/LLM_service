import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "ui/Button.tsx";
import useRegister from "hooks/useRegister.ts";
import Checkbox from "ui/Checkbox.tsx";
import { Roles } from "models/User.ts";

function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setCheckPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [premium, setPremium] = useState(false);
  const { mutateAsync, isPending } = useRegister();
  const navigate = useNavigate();

  const onSubmit = async () => {
    await mutateAsync({
      username,
      password,
      firstName,
      lastName,
      role: premium ? Roles.PAID : Roles.FREE,
    });
    navigate("/login");
  };

  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Register Now
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
        <form>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Username"
              name="username"
              type="text"
              placeholder="Enter Username"
              value={username}
              onInput={(e) => setUsername(e.currentTarget.value)}
            />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Password"
              type="password"
              name="password"
              placeholder="Enter Password"
              value={password}
              onInput={(e) => setPassword(e.currentTarget.value)}
            />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Repeat Password"
              type="password"
              name="repeat_password"
              placeholder="Enter Password"
              value={passwordCheck}
              onInput={(e) => setCheckPassword(e.currentTarget.value)}
            />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="FirstName"
              name="firstname"
              type="text"
              placeholder="Enter Firstname"
              value={firstName}
              onInput={(e) => setFirstName(e.currentTarget.value)}
            />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="LastName"
              name="lastname"
              type="text"
              value={lastName}
              placeholder="Enter Lastname"
              onInput={(e) => setLastName(e.currentTarget.value)}
            />
          </div>
          <div>
            Premium
            <Checkbox onClick={() => setPremium((value) => !value)} checked={premium} />
          </div>
          <Button onClick={onSubmit} disabled={isPending} className="w-full">
            Register
          </Button>
          <div className="text-gray-800 mt-6 text-center">
            Already have an account
            <LinkText to="/login">Log In</LinkText>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;
