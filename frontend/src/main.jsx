import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { Provider } from "react-redux";
import { AuthProvider } from "react-oauth2-code-pkce";

import App from "./App.jsx";
import { store } from "./store/store.js";
import { AuthConfig } from "./store/authConfig.js";

createRoot(document.getElementById("root")).render(

  <Provider store={store}>
    <AuthProvider authConfig={AuthConfig}>
      <App />
    </AuthProvider>
  </Provider>

);
