import React, { FC, useState } from 'react';

interface IUserContext {
  user: IUser | null;
  logOut: () => void;
  updateUser: (user: IUser | null) => void;
}

export const UserContext = React.createContext<IUserContext>({
  user: null,
  logOut: () => null,
  updateUser: () => null,
});

export const UserProvider: FC = ({ children }) => {
  const [user, setUser] = useState<IUser | null>(null);
  const defaultValue: IUserContext = {
    user: user,
    logOut: () => {
      setUser(null);
    },
    updateUser: (user: IUser | null) => {
      setUser(user);
    },
  };

  return (
    <UserContext.Provider value={defaultValue}>{children}</UserContext.Provider>
  );
};
