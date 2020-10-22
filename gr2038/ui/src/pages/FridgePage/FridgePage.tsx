import React, { FC, useContext } from 'react';
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
  const { user } = useContext(UserContext);
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');
  const [unit, setUnit] = useState('');
  const units = ['dl', 'gram', 'stk'];

  const postIngdredient = () => {
    axios
      .post(`/users/${user.username}/ingdredients/add`, {
        quantity: { unit: unit, anount: amount },
        name: name,
      })
      .then(function (res) {
        console.log(res);
        addItem();
      });
  };
  const addItem = () => {
    return user.intgredientContainer.ingredients.map(
      (ing: Ingredient, index: number) => {
        return <Item key={index} item={ing} />;
      }
    );
  };

  return (
    <form className="addIngredientMenu">
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
        onChange={(e) => setAmount(e.target.value)}
      />
      <select
        className="addIngredientElement"
        placeholder="Unit"
        name="Unit"
        value={unit}
        onChange={(e) => setUnit(e.target.value)}
      >
        {units.map((unit: String, index: number) => {
          return <option key={index}>{unit}</option>;
        })}
      </select>
      <input
        type="sumbit"
        onClick={postIngdredient}
        className="addIngredientElement"
      />
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
