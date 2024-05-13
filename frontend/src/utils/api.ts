import { ApiError, NetworkError } from "models/Errors.ts";

export interface ApiRequest {
  url: string;
  token: string | null;
  method?: Request["method"];
  params?: Record<string, string>;
  headers?: HeadersInit;
  mode?: Request["mode"];
  credentials?: Request["credentials"];
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  body?: any;
  redirect?: Request["redirect"];
  /**
   * @default false
   */
  rawBody?: boolean;
  /**
   * @default json
   */
  responseAs?: "arrayBuffer" | "blob" | "formData" | "json" | "text";
  /**
   * @description include default headers
   * - Content-Type: application/json
   * @default true
   */
  includeDefaultHeaders?: boolean;
  /**
   * @default true
   */
  urlObjectInstance?: boolean;
  /**
   * @default true
   */
  trackScreenActivity?: boolean;
}

export const api = async <TData>({
  url,
  token,
  method = "get",
  params = {},
  headers = {},
  credentials,
  body,
  mode,
  redirect,
  rawBody = false,
  responseAs = "json",
  includeDefaultHeaders = true,
  urlObjectInstance = true,
}: ApiRequest): Promise<TData> => {
  const fetchUrl = urlObjectInstance ? new URL(url) : url;
  if (urlObjectInstance) {
    const searchParams = new URLSearchParams(params);
    fetchUrl.search = searchParams.toString();
  }

  const defaultHeaders: HeadersInit = {
    ...(includeDefaultHeaders &&
      !!body &&
      method.toLowerCase() !== "get" &&
      !rawBody && { "Content-Type": "application/json" }),
    ...(!!token && { Authorization: `Bearer ${token}` }),
  };
  try {
    const response = await fetch(fetchUrl, {
      headers: { ...defaultHeaders, ...headers },
      mode,
      method,
      credentials,
      redirect,
      body: rawBody ? body : JSON.stringify(body),
    });

    if (response.status === 401) {
      localStorage.removeItem("token");
    }

    if (!response.ok) {
      const contentType = response.headers.get("Content-Type");

      if (contentType && contentType.includes("text")) {
        const errBody = await response.text();
        throw new ApiError(response, null, errBody);
      }

      const errBody = await response.json();
      throw new ApiError(response, errBody);
    }

    if (response.status === 204) {
      return body || {};
    }

    return await response[responseAs]?.();
  } catch (err) {
    if (err instanceof DOMException && err.name === "AbortError") {
      throw new NetworkError(err);
    }
    throw err;
  }
};
