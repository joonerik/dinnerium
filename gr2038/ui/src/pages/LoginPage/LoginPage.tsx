import React, { useContext, useState } from 'react';
import '../../assets/styles/defaults.scss';
import axios from 'axios';
import UserContext from '../../components/UserContext/UserContext';
import './loginPage.scss';
import { ToastContainer, toast, Zoom, Bounce } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import dinneriumLogo from '../../assets/static/dinnerium-min.png';

const LoginPage = () => {
  const toastUserNotFound = () => {
    toast('Username not found! Please register', {
      className: 'toast-fail',
      draggable: true,
    });
  };

  const [name, setName] = useState<string>('');
  const [isAction, setAction] = useState<string>('');
  const { setUser } = useContext(UserContext);

  const submitForm = (
    event: React.FormEvent<HTMLFormElement>,
    action: String
  ) => {
    event.preventDefault();
    axios.post('/users/' + action, { username: name }).then((res) => {
      setUser(res.data);
    });
  };

  return (
    <div className="login__modal">
      <img src={dinneriumLogo} alt="Logo" className="login__logo" />
      <form method="post" onSubmit={(e) => submitForm(e, isAction)}>
        <input
          onChange={(event) => setName(event.target.value)}
          placeholder="username"
          type="text"
          name="username"
          required
          className="login__modal__input"
          autoFocus
        />

        <div className="login__modal__btnContainer">
          <button
            type="submit"
            value="Login"
            onClick={() => setAction('login')}
          >
            Logg inn
          </button>
          <button
            type="submit"
            value="Register"
            onClick={() => setAction('register')}
          >
            Registrer
          </button>
        </div>
      </form>
      <ToastContainer draggable={false} transition={Zoom} autoClose={8000} />
    </div>
  );
};
export default LoginPage;
