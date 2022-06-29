import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./Dashboard";
import Signin from "./Signin/SignIn";
import NotFoundPage from "./404";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<Dashboard />}>
          <Route path="*" element={<NotFoundPage />}></Route>
        </Route>
        <Route path="/signin" element={<Signin />}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;
