import { StoreHeader } from "@/components/storefront/store-header";
import { LoginHero } from "@/domains/auth/components/login-hero";
import { LoginPanel } from "@/domains/auth/components/login-panel";

export function LoginPage() {
  return (
    <main className="min-h-screen bg-[#efe7df] text-stone-800">
      <StoreHeader />
      <div className="px-3 py-3 sm:px-4 sm:py-4">
        <section className="mx-auto flex min-h-[calc(100vh-1.5rem)] max-w-[95rem] items-stretch overflow-hidden rounded-[2.2rem] bg-white shadow-[0_28px_90px_rgba(25,20,16,0.1)]">
          <div className="grid w-full lg:grid-cols-[1.08fr_0.92fr]">
            <LoginHero />
            <LoginPanel />
          </div>
        </section>
      </div>
    </main>
  );
}
