import React, { useContext, useState } from 'react';
import '../../assets/styles/defaults.scss';
import axios from 'axios';
import { UserContext } from '../../components/UserContext/UserContext';
import './loginPage.scss';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import dinneriumLogo from '../../assets/static/dinnerium-min.png';

const LoginPage = () => {
  const [name, setName] = useState<IUser['username']>('');
  const [isAction, setAction] = useState<'register' | 'login'>('login');
  const { updateUser } = useContext(UserContext);

  const submitForm = (
    event: React.FormEvent<HTMLFormElement>,
    action: 'register' | 'login'
  ) => {
    event.preventDefault();
    axios
      .post('/users/' + action, { username: name })
      .then((res) => {
        updateUser(res.data);
      })
      .catch(() => {
        toast.error('User not found! Please register a user');
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
      <ToastContainer
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />{' '}
    </div>
  );
};
export default LoginPage;
