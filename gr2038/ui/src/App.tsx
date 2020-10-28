import React from 'react';
import NavBar from './components/NavBar/NavBar';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from 'react-router-dom';
import FridgePage from './pages/FridgePage/FridgePage';
import RecipePage from './pages/RecipePage/RecipePage';
import LoginPage from './pages/LoginPage/LoginPage';
import SettingsPage from './pages/SettingsPage/SettingsPage';
//import NewRecipe from './components/NewRecipe/NewRecipe';
import { UserContext } from './components/UserContext/UserContext';
import NewRecipe from './components/NewRecipe/NewRecipe';

function App() {
  const { user } = React.useContext(UserContext);
  return (
    <Router>
      {user === null ? (
        <LoginPage />
      ) : (
        <>
          <NavBar />
          <Switch>
            <Route path="/" exact>
              <Redirect to="/fridge" />
              <FridgePage />
            </Route>
            <Route exact path="/fridge">
              <FridgePage />
            </Route>
            <Route excact path="/settings">
              <SettingsPage />
            </Route>
            <Route excact path="/newRecipe">
              <NewRecipe />
            </Route>
            <Route excact path="/recipes">
              <RecipePage />
            </Route>
          </Switch>
        </>
      )}
    </Router>
  );
}

export default App;
