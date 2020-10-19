import React from 'react';
import NavBar from './components/NavBar/NavBar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import FridgePage from './pages/FridgePage/FridgePage';
import RecipePage from './pages/RecipePage/RecipePage';

function App() {
  return (
    <Router>
      <NavBar />
      <Switch>
        <Route path="/fridge">
          <FridgePage />
        </Route>
        <Route path="/recipes">
          <RecipePage />
        </Route>
        <Route path="/settings">
          <FridgePage />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
