import React, { FC, useState } from 'react';

interface IUserContext {
  user: IUser | null;
  logOut: () => void;
  login: (user: IUser | null) => void;
}

export const UserContext = React.createContext<IUserContext>({
  user: null,
  logOut: () => null,
  login: () => null,
});
export const UserProvider: FC = ({ children }) => {
  const [user, setUser] = useState<IUser | null>(null);
  const defaultValue: IUserContext = {
    user: user,
    logOut: () => {
      setUser(null);
    },
    login: (user: IUser | null) => {
      setUser(user);
    },
  };

  return (
    <UserContext.Provider value={defaultValue}>{children}</UserContext.Provider>
  );
};
