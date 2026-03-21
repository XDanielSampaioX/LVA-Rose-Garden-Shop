import { DEFAULT_BACKEND_URL } from "@/domains/auth/constants/session";

const backendUrl = process.env.NEXT_PUBLIC_API_URL ?? DEFAULT_BACKEND_URL;

export function GoogleLoginButton() {
  return (
    <a
      href={`${backendUrl}/autenticacao/google`}
      className="inline-flex h-11 w-full items-center justify-center gap-3 rounded-[0.75rem] border border-stone-200 bg-white px-5 text-sm font-semibold text-stone-800 shadow-[0_10px_24px_rgba(15,23,42,0.04)] transition duration-200 hover:bg-stone-50 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-[#d39c18] focus-visible:ring-offset-2 focus-visible:ring-offset-white"
    >
      <svg
        aria-hidden="true"
        viewBox="0 0 24 24"
        className="h-5 w-5 shrink-0"
      >
        <path
          fill="#fff"
          d="M21.35 11.1H12v2.95h5.35c-.23 1.4-1.64 4.09-5.35 4.09-3.22 0-5.85-2.67-5.85-5.95S8.78 6.24 12 6.24c1.83 0 3.06.78 3.76 1.45l2.56-2.47C16.69 3.7 14.58 2.75 12 2.75 6.92 2.75 2.75 6.89 2.75 12S6.92 21.25 12 21.25c5.72 0 9.51-4 9.51-9.64 0-.65-.07-1.14-.16-1.51Z"
        />
      </svg>
      Continuar com Google
    </a>
  );
}
