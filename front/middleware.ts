import { NextRequest, NextResponse } from "next/server";
import { SESSION_COOKIE_NAME } from "@/domains/auth/constants/session";

function decodeJwtPayload(token: string) {
  try {
    const [, payload] = token.split(".");

    if (!payload) {
      return null;
    }

    const base64 = payload.replace(/-/g, "+").replace(/_/g, "/");
    const normalized = base64.padEnd(Math.ceil(base64.length / 4) * 4, "=");
    const decoded = atob(normalized);

    return JSON.parse(decoded) as { exp?: number };
  } catch {
    return null;
  }
}

function sessionExpirada(token: string | undefined) {
  if (!token) {
    return true;
  }

  const payload = decodeJwtPayload(token);

  if (!payload?.exp) {
    return true;
  }

  return payload.exp * 1000 <= Date.now();
}

export function middleware(request: NextRequest) {
  const token = request.cookies.get(SESSION_COOKIE_NAME)?.value;
  const expired = sessionExpirada(token);
  const response = NextResponse.next();

  if (expired && token) {
    response.cookies.delete(SESSION_COOKIE_NAME);
    return response;
  }

  return response;
}

export const config = {
  matcher: ["/", "/login", "/catalogo/:path*"],
};
