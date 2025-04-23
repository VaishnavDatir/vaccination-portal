import { Link } from "react-router-dom";

function NavBar() {
  return (
    <nav className="navbar">
      <nav className="navbar-brand">
        <Link to="/">Home</Link>
      </nav>
    </nav>
  );
}

export default NavBar;
