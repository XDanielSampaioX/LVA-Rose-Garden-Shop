import type { ReactNode } from "react";

interface PillBadgeProps {
  children: ReactNode;
  tone?: "gold" | "dark" | "rose" | "neutral";
  className?: string;
}

const tones = {
  gold: "bg-[#d4a742] text-[#24180b]",
  dark: "bg-[#161616] text-white",
  rose: "bg-[#ef8eb0] text-white",
  neutral: "bg-[#f2ece4] text-stone-700",
};

export function PillBadge({
  children,
  tone = "neutral",
  className = "",
}: PillBadgeProps) {
  return (
    <span
      className={`inline-flex rounded-full px-3 py-1 text-[11px] font-semibold ${tones[tone]} ${className}`}
    >
      {children}
    </span>
  );
}
