import React, { FC, useContext, useEffect } from 'react';
import axios from 'axios';

import './FridgePage.scss';
import { useState } from 'react';
import UserContext from '../../components/UserContext/UserContext';

interface IItem {
  item: { quantity: { unit: string; amount: number }; name: string };
}

const Item: FC<IItem> = ({ item }) => {
  return (
    <div className="item">
      {item.quantity.amount}
      {item.quantity.unit} {item.name}
    </div>
  );
};

function Menu() {
  const { user, setUser } = useContext(UserContext);
  const [name, setName] = useState('');
  const [amount, setAmount] = useState(0);
  const [unit, setUnit] = useState('');
  const [units, setUnits] = useState<string[]>([]);

  useEffect(() => {
    axios.get('/units').then((response) => {
      setUnits(response.data.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  const postIngdredient = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (unit !== "None" && amount && name) {
        axios
          .post(`/users/${user.username}/ingredients/add`, {
            quantity: { unit: unit, amount: amount },
            name: name,
          })
          .then((response) => {
            setUser(response.data);
          });
    } else {
        //Add some actual errorhandling.
        alert("You need to fill out all the fields");
    }
  };

  return (
    <form
      className="addIngredientMenu"
      onSubmit={(event) => postIngdredient(event)}
    >
      <input
        className="addIngredientElement"
        placeholder="Name"
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        className="addIngredientElement"
        placeholder="Quantity"
        type="number"
        value={amount}
        onChange={(e) => setAmount(parseFloat(e.target.value))}
      />
      <select
        className="addIngredientElement"
        placeholder="unit"
        defaultValue={'None'}
        name="Unit"
        onChange={(e) => setUnit(e.target.value)}
      >
        <option value="None" disabled>
            Units
        </option>
        {units.map((item: string, index: number) => {
          return (
            <option key={index} value={item}>
              {item}
            </option>
          );
        })}
      </select>
      <button className="addIngredientElement">Add</button>
    </form>
  );
}

function FridgePage() {
  const { user } = useContext(UserContext);

  return (
    <div className="fridgePage">
      <Menu />
      <div className="itemList">
        {user.ingredientContainer.ingredients.map(
          (ing: Ingredient, index: number) => {
            return <Item key={index} item={ing} />;
          }
        )}
      </div>
    </div>
  );
}

export default FridgePage;
