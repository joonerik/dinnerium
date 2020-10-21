import React from 'react';
const emptyUser = { username: 'null' } as IUser;

const UserContext = React.createContext<IUser>(emptyUser);

export default UserContext;
