import React, { useMemo, useState } from 'react';
import NavBar from './components/NavBar/NavBar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import FridgePage from './pages/FridgePage/FridgePage';
import RecipePage from './pages/RecipePage/RecipePage';
import LoginPage from './pages/LoginPage/LoginPage';
import UserContext from './components/UserContext/UserContext';

function App() {
  const emptyUser = { username: 'null' } as IUser;
  const [user, setUser] = useState<IUser>(emptyUser);

  const value = useMemo(() => ({ user, setUser }), [user, setUser]);
  return (
    <Router>
      <UserContext.Provider value={value.user}>
        {user === null ? (
          <LoginPage />
        ) : (
          <>
            <NavBar />
            <Switch>
              <Route path="/fridge">
                <FridgePage />
              </Route>
              <Route path="/recipes">
                <RecipePage />
              </Route>
              <Route path="/settings">
                <LoginPage />
              </Route>
            </Switch>
          </>
        )}
      </UserContext.Provider>
    </Router>
  );
}

export default App;
