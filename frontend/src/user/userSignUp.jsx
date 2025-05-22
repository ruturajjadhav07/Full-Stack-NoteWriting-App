import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import backgroundImg from "../assets/background.avif";
import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "../server/Server";

const UserSignup = () => {
  const [signup, setSignup] = useState({ username: "", password: "" });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSignup((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("", signup); // "" points to baseURL "/user/"
      console.log("Signup success:", response.data);
      alert("User created successfully!");
    } catch (error) {
      console.error("Signup error:", error);
      alert("Error signing up.");
    }
  };

  return (
    <div
      style={{
        backgroundImage: `url(${backgroundImg})`,
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        height: "100vh",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <div className="container col-md-4">
        <form
          onSubmit={handleSubmit}
          className="border rounded shadow p-4"
          style={{
            backgroundColor: "rgba(255, 255, 255, 0.3)",
            backdropFilter: "blur(10px)",
            borderRadius: "10px",
          }}
        >
          <h2 className="text-center mb-4">User Signup</h2>
          <div className="form-group my-2">
            <input
              className="form-control"
              type="text"
              name="username"
              placeholder="Username"
              value={signup.username}
              onChange={handleChange}
            />
          </div>

          <div className="form-group my-2">
            <input
              className="form-control"
              type="password"
              name="password"
              placeholder="Password"
              value={signup.password}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <span>
              <Link to={"/"}>Login here</Link>
            </span>
          </div>

          <div className="form-group mt-3">
            <button className="btn btn-primary w-100" type="submit">
              Signup
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserSignup;
