import React, { useContext } from 'react';
import { UserContext } from '../../components/UserContext/UserContext';
import { useHistory } from 'react-router-dom';
import './settingsPage.scss';

const SettingsPage = () => {
  const { user, logOut } = useContext(UserContext);
  const history = useHistory();

  const handleLogout = () => {
    history.push('/');
    logOut();
  };

  return (
    <section className="logout__container">
      <header className="logout__header">
        <h1>Hi, {user?.username}</h1>
        <h2>Are you sure you want to log out?</h2>
      </header>
      <button onClick={handleLogout}>Logout</button>
    </section>
  );
};

export default SettingsPage;
