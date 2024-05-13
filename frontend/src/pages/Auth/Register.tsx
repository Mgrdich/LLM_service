import InputWithLabel from "ui/InputWithLabel.tsx";
import LinkText from "ui/LinkText.tsx";
import { useNavigate } from "react-router-dom";
import useRegister from "hooks/useRegister.ts";
import Checkbox from "ui/Checkbox.tsx";
import { Roles } from "models/User.ts";
import { SubmitHandler, useForm } from "react-hook-form";
import ErrorLabel from "ui/ErrorLabel.tsx";
import FormSubmitButton from "ui/FormSubmitButton.tsx";
import { z, ZodType } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";

type RegisterForm = {
  username: string;
  password: string;
  confirmPassword: string;
  firstName: string;
  lastName: string;
  premium: boolean;
};

const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

export const RegisterSchema: ZodType<RegisterForm> = z
  .object({
    username: z.string().min(4).max(30),
    password: z.string().min(8).max(30).regex(passwordRegex),
    confirmPassword: z.string().min(8).max(30).regex(passwordRegex),
    firstName: z.string().min(3).max(30),
    lastName: z.string().min(3).max(30),
    premium: z.boolean(),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "Passwords do not match",
    path: ["confirmPassword"], // path of error
  });

function Register() {
  const { mutateAsync } = useRegister();
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting, isValid },
  } = useForm<RegisterForm>({ mode: "all", resolver: zodResolver(RegisterSchema) });

  const navigate = useNavigate();

  const onSubmit: SubmitHandler<RegisterForm> = async ({ username, password, firstName, lastName, premium }) => {
    if (isValid) return;

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
      <div className="block p-6 rounded-lg shadow-lg bg-white">
        <form onSubmit={handleSubmit(onSubmit)}>
          <div className="form-group mb-6">
            <InputWithLabel label="Username" type="text" placeholder="Enter Username" {...register("username")} />
            <ErrorLabel error={errors.username} />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="Password" type="password" placeholder="Enter Password" {...register("password")} />
            <ErrorLabel error={errors.password} />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel
              label="Repeat Password"
              type="password"
              placeholder="Enter Password"
              {...register("confirmPassword")}
            />
            <ErrorLabel error={errors.confirmPassword} />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="FirstName" type="text" placeholder="Enter Firstname" {...register("firstName")} />
            <ErrorLabel error={errors.firstName} />
          </div>
          <div className="form-group mb-6">
            <InputWithLabel label="LastName" type="text" placeholder="Enter Lastname" {...register("lastName")} />
            <ErrorLabel error={errors.lastName} />
          </div>
          <div>
            Premium
            <Checkbox {...register("premium")} />
            <ErrorLabel error={errors.premium} />
          </div>
          <FormSubmitButton disabled={isSubmitting || !isValid}>Register</FormSubmitButton>
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
