import React, { FC, useContext, useEffect } from 'react';
import axios from 'axios';

import './FridgePage.scss';
import { useState } from 'react';
import UserContext from '../../components/UserContext/UserContext';
import { isAsExpression } from 'typescript';

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
  const [units, setUnits] = useState<string[]>([]);

  useEffect(() => {
    axios.get('/units').then((response) => {
      const list: string = response.data;
      setUnits(list.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  const postIngdredient = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    axios
      .post(`/users/${user.username}/ingredients/add`, {
        quantity: { unit: unit, amount: amount },
        name: name,
      })
      .then(function (res) {
        console.log(res);
        addItem();
      });
  };
  const addItem = () => {
    return user.ingredientContainer.ingredients.map(
      (ing: Ingredient, index: number) => {
        return <Item key={index} item={ing} />;
      }
    );
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
        onChange={(e) => setAmount(e.target.value)}
      />
      <select
        className="addIngredientElement"
        placeholder="Unit"
        name="Unit"
        value={unit}
        onChange={(e) => setUnit(e.target.value)}
      >
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
