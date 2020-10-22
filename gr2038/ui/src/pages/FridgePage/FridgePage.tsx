import React, { useContext } from 'react';
import UserContext from '../../components/UserContext/UserContext';

const FridgePage = () => {
  const { user } = useContext(UserContext);
  return (
    <div>
      <h1>Fridge Page</h1>
      <p>
        Halla!!
        {user.username}
      </p>
    </div>
  );
};
export default FridgePage;
