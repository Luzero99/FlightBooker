import { ThemeOptions } from "@mui/material/styles";
import { createTheme } from "@mui/material";

export const themeOptions: ThemeOptions = createTheme({
  palette: {
    mode: "light",
    primary: {
      main: "#087e8b",
    },
    secondary: {
      main: "#ff5a5f",
    },
  },
});
