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
        let message: string;
        if (action === 'register') {
          message = 'Username already in use, please choose another one';
        } else {
          message = 'User not found! Please register a user';
        }
        toast.error(message);
      });
  };

  const checkValidity = (event: React.ChangeEvent<HTMLInputElement>) => {
    event.target.setCustomValidity('');
    if (!event.target.validity.valid) {
      if (event.target.value.match(/(æ|ø|å)/)) {
        event.target.setCustomValidity('Only english characters are allowed');
      } else if (event.target.value.match(/(\s)/)) {
        event.target.setCustomValidity('Username cannot contain spaces');
      } else if (event.target.value.length > 15) {
        event.target.setCustomValidity(
          'Username must be less than 15 characters'
        );
      } else if (event.target.value.length < 3) {
        event.target.setCustomValidity(
          'Username must be longer than 3 characters'
        );
      } else {
        event.target.setCustomValidity('Username must be valid');
      }
    }
  };

  return (
    <div className="login__modal">
      <img src={dinneriumLogo} alt="Logo" className="login__logo" />
      <form method="post" onSubmit={(e) => submitForm(e, isAction)}>
        <input
          onChange={(event) => {
            checkValidity(event);
            setName(event.target.value);
          }}
          placeholder="username"
          type="text"
          name="username"
          required
          className="login__modal__input"
          pattern="^(?=.{3,15}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
          autoFocus
        />

        <div className="login__modal__btnContainer">
          <button
            type="submit"
            value="Login"
            onClick={() => {
              setAction('login');
            }}
          >
            Logg inn
          </button>
          <button
            type="submit"
            value="Register"
            onClick={() => {
              setAction('register');
            }}
          >
            Registrer
          </button>
        </div>
      </form>
      <ToastContainer
        containerId="toastContainer"
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
