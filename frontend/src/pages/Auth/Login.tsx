import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import ErrorLabel from "ui/ErrorLabel.tsx";
import FormSubmitButton from "ui/FormSubmitButton.tsx";
import { z, ZodType } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import useLogin from "hooks/api/useLogin.ts";

type LoginForm = {
  username: string;
  password: string;
};

export const LoginSchema: ZodType<LoginForm> = z.object({
  username: z.string().min(4, "Minimum length should be 4").max(30, "Maximum length should be 30"),
  password: z.string().min(8, "Minimum length should be 8").max(30, "Minimum length should be 30"),
});

export default function Login() {
  const {
    register,
    handleSubmit,
    formState: { errors, isValid, isSubmitting },
  } = useForm<LoginForm>({ mode: "all", resolver: zodResolver(LoginSchema) });

  const { mutateAsync } = useLogin();
  const navigate = useNavigate();

  const onSubmit: SubmitHandler<LoginForm> = async ({ username, password }) => {
    if (!isValid) return;

    await mutateAsync({ username, password });
    navigate("/conversation");
  };

  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Welcome Back :)
      </h1>
      <div className="block mb-10 p-6 rounded-lg shadow-lg bg-white max-w-lg w-full">
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="form-group mb-6">
            <InputWithLabel label="Username" type="text" placeholder="Enter Username" {...register("username")} />
            <ErrorLabel error={errors.username} />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="Password" type="password" placeholder="Enter Password" {...register("password")} />
            <ErrorLabel error={errors.password} />
          </div>
          <div className="flex justify-between items-center mb-6" />
          <FormSubmitButton disabled={isSubmitting || !isValid} isLoading={isSubmitting}>
            Sign In
          </FormSubmitButton>
          <div className="text-gray-800 mt-6 text-center">
            Not a member?
            <LinkText to="/register">Register</LinkText>
          </div>
        </form>
      </div>
    </div>
  );
}
