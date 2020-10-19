import React, { useState } from 'react';
import '../../assets/styles/defaults.scss';
import axios from 'axios';
interface IItem {
  item: { item: { text: string } };
}
const LoginPage = () => {
  //const [isLoginStatus, setLoginStatus] = useState(false);
  const [isUser, setUser] = useState<string>('');
  const [name, setName] = useState<string>('');
  const [isAction, setAction] = useState<string>('');

  const submitForm = (
    event: React.FormEvent<HTMLFormElement>,
    action: String
  ) => {
    event.preventDefault();
    axios.post('/users/' + action, { username: name }).then(function (res) {
      console.log(res);
    });
  };
  const getUser = async (user: string) => {
    console.log(user);

    const response = await fetch(`/users/${user}`, {
      mode: 'no-cors',
    });

    const data = await response.json();
    console.log(data);
  };

  return (
    <div className="login__modal">
      <form method="post" onSubmit={(e) => submitForm(e, isAction)}>
        <input
          onChange={(event) => setName(event.target.value)}
          placeholder="username"
          type="text"
          name="username"
          required
        />
        <br />
        <input type="submit" value="Login" onClick={() => setAction('login')} />
        <input
          type="submit"
          value="Register"
          onClick={() => setAction('register')}
        />
      </form>
    </div>
  );
};
export default LoginPage;
