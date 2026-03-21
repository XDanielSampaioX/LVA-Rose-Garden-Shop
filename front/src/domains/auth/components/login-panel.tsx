import Link from "next/link";
import { GoogleLoginButton } from "@/domains/auth/components/google-login-button";

function FormField({
  label,
  placeholder,
}: {
  label: string;
  placeholder: string;
}) {
  return (
    <label className="block space-y-2">
      <span className="text-[11px] font-semibold text-stone-700">{label}</span>
      <input
        type="text"
        placeholder={placeholder}
        className="h-10 w-full rounded-[0.7rem] border border-stone-200 bg-white px-3 text-sm text-stone-800 outline-none transition placeholder:text-stone-400 focus:border-[#d5a021] focus:ring-2 focus:ring-[#f2d27a]/35"
      />
    </label>
  );
}

export function LoginPanel() {
  return (
    <aside className="mx-auto flex w-full max-w-[28rem] flex-col justify-center rounded-[2rem] bg-white px-7 py-10 shadow-[0_30px_80px_rgba(34,22,16,0.08)] sm:px-9 lg:min-h-[760px] lg:max-w-none lg:rounded-[2rem]">
      <div className="mx-auto w-full max-w-[20rem]">
        <div className="flex justify-center">
          <div className="flex h-11 w-11 items-center justify-center rounded-full bg-[#171312] text-[#f04d9a] shadow-[0_8px_20px_rgba(0,0,0,0.12)]">
            <span className="text-xl leading-none">✿</span>
          </div>
        </div>

        <div className="mt-5 text-center">
          <h1 className="text-[2rem] font-semibold tracking-tight text-stone-950">
            Crie sua conta
          </h1>
          <p className="mt-2 text-xs text-stone-500">
            Cadastre-se para explorar nossas rosas
          </p>
        </div>

        <div className="mt-6 grid grid-cols-2 rounded-full bg-[#f1ede8] p-1">
          <Link
            href="/login"
            className="rounded-full px-4 py-2 text-center text-sm font-medium text-stone-600 transition hover:text-stone-900"
          >
            Entrar
          </Link>
          <button
            type="button"
            className="rounded-full bg-[#171312] px-4 py-2 text-sm font-semibold text-white shadow-[0_8px_18px_rgba(23,19,18,0.18)]"
          >
            Cadastrar
          </button>
        </div>

        <div className="mt-5">
          <GoogleLoginButton />
        </div>

        <div className="my-6 flex items-center gap-3">
          <div className="h-px flex-1 bg-stone-200" />
          <span className="text-[10px] uppercase tracking-[0.28em] text-stone-400">
            ou
          </span>
          <div className="h-px flex-1 bg-stone-200" />
        </div>

        <form className="space-y-4">
          <FormField label="Nome completo" placeholder="Seu nome" />
          <FormField label="E-mail" placeholder="seu@email.com" />
          <FormField label="Senha" placeholder="••••••••" />
          <FormField label="Confirmar senha" placeholder="••••••••" />

          <button
            type="button"
            className="mt-2 h-11 w-full rounded-[0.75rem] bg-[#d39c18] text-sm font-semibold text-[#20150b] shadow-[0_12px_24px_rgba(211,156,24,0.24)] transition hover:bg-[#c78f0e]"
          >
            Criar conta
          </button>
        </form>

        <p className="mt-5 text-center text-[11px] text-stone-400">
          Já tem conta?{" "}
          <Link href="/login" className="font-semibold text-[#d5727b] hover:text-[#bb4c5b]">
            Entrar
          </Link>
        </p>
      </div>
    </aside>
  );
}
