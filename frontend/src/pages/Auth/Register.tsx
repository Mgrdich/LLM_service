import InputWithLabel from "ui/InputWithLabel.tsx";
import Link from "ui/Link.tsx";
import FormSubmitButton from "./components/FormSubmitButton.tsx";

function Register() {
  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Register Now
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
        <form>
          <div className="form-group mb-6">
            <InputWithLabel label="Email" name="email" type="text" placeholder="Enter Email" />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="Password" type="password" name="password" placeholder="Enter Password" />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="Repeat Password" type="password" name="password" placeholder="Enter Same Password" />
          </div>
          <FormSubmitButton>Register</FormSubmitButton>
          <div className="text-gray-800 mt-6 text-center">
            Already have an account
            <Link href="/login">Log In</Link>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;
