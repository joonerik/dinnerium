import React, { useContext } from 'react';
import { UserContext } from '../../components/UserContext/UserContext';
import './settingsPage.scss';

const SettingsPage = () => {
  const { user, logOut } = useContext(UserContext);
  return (
    <section className="logout__container">
      <header className="logout__header">
        <h1>Hi, {user?.username}</h1>
        <h2>Are you sure you want to log out?</h2>
      </header>
      <button onClick={logOut}>Logout</button>
    </section>
  );
};

export default SettingsPage;
