import { useAuthContext, AuthProvider, TAuthConfig, TRefreshTokenExpiredEvent } from "react-oauth2-code-pkce"

export const AuthConfig = {
  clientId: 'oauth2-ai-fitness-app',
  authorizationEndpoint: 'http://localhost:8080/realms/master/protocol/openid-connect/auth',
  tokenEndpoint: 'http://localhost:8080/realms/master/protocol/openid-connect/token',
  redirectUri: 'http://localhost:5173/',
  scope: 'openid profile email offline_access',
  onRefreshTokenExpire: (event) => event.logIn(),
}
