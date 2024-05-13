import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import useLogin from "hooks/useLogin.ts";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import ErrorLabel from "ui/ErrorLabel.tsx";
import FormSubmitButton from "ui/FormSubmitButton.tsx";
import { z, ZodType } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";

type LoginForm = {
  username: string;
  password: string;
};

export const LoginSchema: ZodType<LoginForm> = z.object({
  username: z.string().min(4).max(30),
  password: z
    .string()
    .min(8)
    .max(30)
    .regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/),
});

export default function Login() {
  const {
    register,
    handleSubmit,
    formState: { errors, isLoading, isValid },
  } = useForm<LoginForm>({ resolver: zodResolver(LoginSchema) });

  const { mutateAsync } = useLogin();
  const navigate = useNavigate();

  const onSubmit: SubmitHandler<LoginForm> = async ({ username, password }) => {
    await mutateAsync({ username, password });
    navigate("/conversation");
  };

  return (
    <div className="bg-neutral-50 min-h-screen flex flex-col items-center justify-center dark:bg-neutral-900">
      <h1 className="mt-0 mb-16 text-5xl text-white font-bold tracking-tight md:text-5xl xl:text-5xl self-center">
        Welcome Back :)
      </h1>
      <div className="block p-6 rounded-lg shadow-lg bg-white max-w-sm">
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
          <FormSubmitButton disabled={isLoading || !isValid}>Sign In</FormSubmitButton>
          <div className="text-gray-800 mt-6 text-center">
            Not a member?
            <LinkText to="/register">Register</LinkText>
          </div>
        </form>
      </div>
    </div>
  );
}
