import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./Dashboard";
import Signin from "./Signin/SignIn";
import NotFoundPage from "./404";
import Board from "./Board/index";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/*" element={<Dashboard />}>
          <Route path="board" element={<Board />}></Route>
          <Route path="*" element={<NotFoundPage />}></Route>
        </Route>
        <Route path="/signin" element={<Signin />}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default App;
