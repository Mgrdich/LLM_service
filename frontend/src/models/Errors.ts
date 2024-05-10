export type ErrorType = {
  code: number;
  detail: string;
  title: string;
};

export interface ErrorResponse {
  errors: ErrorType[];
  data?: unknown;
  message: string;
  type: string;
}

export class ApiError extends Error {
  private readonly _apiError: ErrorResponse | null;

  private readonly _response: Response;

  public get apiResponse(): ErrorResponse | null {
    return this._apiError;
  }

  public get response(): Response {
    return this._response;
  }

  public getFirstError(): ErrorType | undefined {
    return this._apiError?.errors.at(0);
  }

  public getAllErrors(): ErrorType[] | undefined {
    return this._apiError?.errors;
  }

  constructor(response: Response, apiError: ErrorResponse | null, message?: string) {
    super(message);
    this._apiError = apiError;
    this._response = response;
  }
}

export class NetworkError extends Error {
  private readonly _error: DOMException;

  public get error(): DOMException {
    return this._error;
  }

  constructor(error: DOMException, message?: string) {
    super(message);
    this._error = error;
  }
}
