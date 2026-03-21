import type { ReactNode } from "react";

interface PrimaryButtonProps {
  children: ReactNode;
  href?: string;
  type?: "button" | "submit";
  variant?: "gold" | "ghost" | "dark";
  className?: string;
  onClick?: () => void;
}

const variants = {
  gold: "bg-[#d4a742] text-[#1f140b] shadow-[0_14px_32px_rgba(212,167,66,0.24)] hover:bg-[#c79a33]",
  ghost: "border border-white/18 bg-white/10 text-white hover:bg-white/16",
  dark: "bg-[#1d140f] text-white hover:bg-[#2a1d15]",
};

export function PrimaryButton({
  children,
  href,
  type = "button",
  variant = "gold",
  className = "",
  onClick,
}: PrimaryButtonProps) {
  const classes = `inline-flex items-center justify-center rounded-[1rem] px-5 py-3 text-sm font-semibold transition ${variants[variant]} ${className}`;

  if (href) {
    return (
      <a href={href} className={classes}>
        {children}
      </a>
    );
  }

  return (
    <button type={type} onClick={onClick} className={classes}>
      {children}
    </button>
  );
}
