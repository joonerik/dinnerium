import React from 'react';
import './navBar.scss';
import { Link, useLocation } from 'react-router-dom';
const NavBar = () => {
  const location = useLocation();
  const isActive = (path: string) => location.pathname === path;
  return (
    <nav className="nav">
      <ul className="nav__ul">
        <li className={isActive('/fridge') ? 'active' : ''}>
          <Link to="/fridge">Fridge</Link>
        </li>
        <li className={isActive('/recipes') ? 'active' : ''}>
          <Link to="/recipes">Recipes</Link>
        </li>
        <li className={isActive('/settings') ? 'active' : ''}>
          <Link to="/settings">Settings</Link>
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;
