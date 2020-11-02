import React from 'react';
import './navBar.scss';
import { Link, useLocation } from 'react-router-dom';
import logo from '../../assets/static/dinnerium-min.png';

const NavBar = () => {
  const location = useLocation();
  const isActive = (path: string) => location.pathname === path;

  return (
    <nav className="nav">
      <ul className="nav__ul">
        <li>
          <Link to="/fridge">
            <img src={logo} alt="dinnerium logo" className="nav__logo" />
          </Link>
        </li>
        <li>
          <ul>
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
        </li>
      </ul>
    </nav>
  );
};

export default NavBar;
