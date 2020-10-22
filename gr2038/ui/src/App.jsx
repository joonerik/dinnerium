import React, { useMemo, useState } from 'react';
import NavBar from './components/NavBar/NavBar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import FridgePage from './pages/FridgePage/FridgePage';
import RecipePage from './pages/RecipePage/RecipePage';
import LoginPage from './pages/LoginPage/LoginPage';
import UserContext from './components/UserContext/UserContext';

function App() {
  const [user, setUser] = useState(null);

  const value = useMemo(() => ({ user, setUser }), [user, setUser]);
  return (
    <Router>
      <UserContext.Provider value={value}>
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
              <Route path="/newRecipe">
                <RecipePage />
              </Route>
            </Switch>
          </>
        )}
      </UserContext.Provider>
    </Router>
  );
}

export default App;