import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import { useForm } from "react-hook-form";
import ErrorLabel from "ui/ErrorLabel.tsx";
import FormSubmitButton from "ui/FormSubmitButton.tsx";

type ForgotPasswordForm = {
  username: string;
};

function ForgotPassword() {
  const {
    register,
    formState: { errors },
  } = useForm<ForgotPasswordForm>();
  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Forgot your password huh ;)
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
        <form>
          <div className="form-group mb-6">
            <InputWithLabel label="username" type="text" placeholder="Enter username" {...register("username")} />
            <ErrorLabel error={errors.username} />
          </div>
          <FormSubmitButton>Submit</FormSubmitButton>
          <div className="text-gray-800 mt-6 text-center">
            Go to
            <LinkText to="/login">Login</LinkText>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ForgotPassword;
