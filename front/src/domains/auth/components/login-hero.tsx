const boutiqueFlowers = [
  "left-[8%] top-[12%] h-28 w-20 rotate-[-16deg] from-[#f8c7d9] via-[#f36e9d] to-[#8f1239]",
  "left-[20%] bottom-[18%] h-32 w-24 rotate-[10deg] from-[#ffd4e2] via-[#fb7185] to-[#9f1239]",
  "right-[10%] top-[22%] h-32 w-24 rotate-[18deg] from-[#fbcfe8] via-[#ec4899] to-[#7a1838]",
  "right-[18%] bottom-[12%] h-24 w-18 rotate-[-10deg] from-[#ffe4ec] via-[#fb7185] to-[#be123c]",
  "left-[38%] top-[30%] h-20 w-14 rotate-[22deg] from-[#f9a8d4] via-[#fb7185] to-[#881337]",
];

function BoutiqueFlower({ className }: { className: string }) {
  return (
    <div className={`absolute ${className}`}>
      <div className="relative h-full w-full">
        <div className="absolute left-1/2 top-1/2 h-[56%] w-[56%] -translate-x-1/2 -translate-y-1/2 rounded-full bg-[radial-gradient(circle,#fff7ed_0%,#fbbf24_55%,#f59e0b_100%)] shadow-[0_0_18px_rgba(251,191,36,0.35)]" />
        <div className="absolute left-1/2 top-[6%] h-[42%] w-[34%] -translate-x-1/2 rounded-[50%_50%_42%_42%] bg-gradient-to-b shadow-[0_10px_24px_rgba(0,0,0,0.22)]" />
        <div className="absolute bottom-[6%] left-1/2 h-[42%] w-[34%] -translate-x-1/2 rounded-[42%_42%_50%_50%] bg-gradient-to-t shadow-[0_-10px_24px_rgba(0,0,0,0.18)]" />
        <div className="absolute left-[4%] top-1/2 h-[34%] w-[42%] -translate-y-1/2 rounded-[50%_42%_42%_50%] bg-gradient-to-r shadow-[0_8px_20px_rgba(0,0,0,0.18)]" />
        <div className="absolute right-[4%] top-1/2 h-[34%] w-[42%] -translate-y-1/2 rounded-[42%_50%_50%_42%] bg-gradient-to-l shadow-[0_8px_20px_rgba(0,0,0,0.18)]" />
        <div className="absolute bottom-[-18%] left-1/2 h-[42%] w-[6%] -translate-x-1/2 rounded-full bg-[linear-gradient(180deg,#32543a,#7ca36d)]" />
        <div className="absolute bottom-[-6%] left-[32%] h-[18%] w-[28%] -rotate-[24deg] rounded-full bg-[linear-gradient(180deg,#83b86c,#32543a)] blur-[0.5px]" />
      </div>
    </div>
  );
}

export function LoginHero() {
  return (
    <section className="relative hidden min-h-[760px] overflow-hidden rounded-[2rem] border border-[#3d2a1f] bg-[#12100f] shadow-[0_28px_90px_rgba(17,12,10,0.42)] lg:block">
      <div className="absolute inset-0 bg-[linear-gradient(180deg,rgba(0,0,0,0.08),rgba(0,0,0,0.58)),radial-gradient(circle_at_30%_20%,rgba(214,132,46,0.34),transparent_22%),radial-gradient(circle_at_70%_18%,rgba(255,214,170,0.12),transparent_18%),linear-gradient(135deg,#1a1412_0%,#0e0c0b_58%,#17110f_100%)]" />
      <div className="absolute inset-y-0 left-0 w-[28%] border-r border-[#6a4623]/55 bg-[linear-gradient(180deg,rgba(24,19,16,0.92),rgba(8,8,8,0.78))]" />
      <div className="absolute inset-y-0 left-[28%] w-[10%] border-r border-[#6a4623]/45 bg-[linear-gradient(180deg,rgba(17,14,12,0.78),rgba(7,7,7,0.45))]" />
      <div className="absolute left-[6%] right-[12%] top-[8%] h-[10%] border border-[#b67a2d]/65" />
      <div className="absolute left-[10%] top-[13%] h-3 w-3 rounded-full bg-[#ffd48f] shadow-[0_0_16px_rgba(255,212,143,0.7)]" />
      <div className="absolute left-[26%] top-[14%] h-3 w-3 rounded-full bg-[#ffd48f] shadow-[0_0_16px_rgba(255,212,143,0.7)]" />
      <div className="absolute left-[41%] top-[12%] h-[56%] w-[34%] rounded-b-[1.8rem] rounded-t-[1rem] border border-[#7b542b]/55 bg-[linear-gradient(180deg,rgba(255,206,148,0.28),rgba(255,166,77,0.08)_45%,rgba(0,0,0,0.12)_100%)] shadow-[inset_0_0_40px_rgba(255,187,102,0.18),0_0_60px_rgba(255,166,77,0.08)]" />
      <div className="absolute left-[44%] top-[15%] h-[48%] w-[28%] rounded-b-[1.5rem] border border-[#b97a34]/55 bg-[radial-gradient(circle_at_center,rgba(255,214,153,0.82),rgba(255,162,56,0.5)_45%,rgba(60,28,10,0.18)_100%)] blur-[1px]" />
      <div className="absolute bottom-[10%] left-[28%] h-[34%] w-[38%] rounded-[1.25rem] bg-[linear-gradient(180deg,#faf4ef,#efe6df_58%,#d4c7bd_100%)] shadow-[0_24px_40px_rgba(0,0,0,0.35)]" />
      <div className="absolute bottom-[10%] left-[28%] h-[6%] w-[38%] bg-[linear-gradient(180deg,rgba(255,255,255,0.22),rgba(0,0,0,0.12))]" />
      <div className="absolute bottom-[10%] left-[62%] h-[32%] w-[4%] bg-[linear-gradient(180deg,#d9ccc2,#8f8177)]" />
      <div className="absolute bottom-[10%] left-[30%] h-[32%] w-[4%] bg-[linear-gradient(180deg,#d9ccc2,#8f8177)]" />

      {boutiqueFlowers.map((flower) => (
        <BoutiqueFlower key={flower} className={flower} />
      ))}

      <div className="absolute left-[14%] top-[44%] h-44 w-28 rounded-[2rem] bg-[radial-gradient(circle_at_40%_20%,rgba(255,242,225,0.24),rgba(255,242,225,0.04)_45%,transparent_70%)] blur-sm" />
      <div className="absolute right-[7%] top-[38%] h-52 w-32 rounded-[2rem] bg-[radial-gradient(circle_at_50%_18%,rgba(255,230,236,0.28),rgba(255,230,236,0.06)_42%,transparent_70%)] blur-sm" />

      <div className="absolute inset-x-0 bottom-0 bg-[linear-gradient(180deg,transparent,rgba(0,0,0,0.5)_58%,rgba(0,0,0,0.9)_100%)] px-8 pb-10 pt-24 text-white">
        <div className="max-w-sm space-y-3">
          <h2 className="font-[family-name:var(--font-display)] text-4xl font-semibold leading-none">
            Rosas do Deserto
          </h2>
          <p className="text-sm leading-6 text-white/82">
            A beleza rara do deserto cultivada com amor e dedicação. Encontre a rosa perfeita para você.
          </p>
        </div>
      </div>
    </section>
  );
}
