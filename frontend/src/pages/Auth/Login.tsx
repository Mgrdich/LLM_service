import InputWithLabel from "ui/InputWithLabel.tsx";
import Checkbox from "ui/Checkbox.tsx";
import LinkText from "ui/LinkText.tsx";
import FormSubmitButton from "./components/FormSubmitButton.tsx";

function Login() {
  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Welcome Back :)
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
        <form>
          <div className="form-group mb-6">
            <InputWithLabel label="Email" name="email" type="text" placeholder="Enter Email" />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="Password" type="password" name="password" placeholder="Enter Password" />
          </div>
          <div className="flex justify-between items-center mb-6">
            <div className="form-group form-check">
              <Checkbox name="remeber_me" />
              <span className="form-check-label inline-block text-gray-800">Remember me</span>
            </div>
            <div className="ml-4">
              <LinkText to="/forgot-password">Forgot password ?</LinkText>
            </div>
          </div>
          <FormSubmitButton>Sign In</FormSubmitButton>
          <div className="text-gray-800 mt-6 text-center">
            Not a member?
            <LinkText to="/register">Register</LinkText>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
