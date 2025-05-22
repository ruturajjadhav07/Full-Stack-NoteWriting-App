import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import UserLogin from "./user/userLogin";
import UserSignup from "./user/userSignUp";

const App = () => {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<UserLogin />} />
          <Route path="/signup" element={<UserSignup />} />
        </Routes>
      </Router>
    </div>
  );
};

export default App;
