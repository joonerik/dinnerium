import React, { FC, useContext, useEffect } from 'react';
import axios from 'axios';

import './FridgePage.scss';
import { useState } from 'react';
import { UserContext } from '../../components/UserContext/UserContext';

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
  const { user, updateUser } = useContext(UserContext);
  const [units, setUnits] = useState<string[]>([]);

  useEffect(() => {
    axios.get('/units').then((response) => {
      setUnits(response.data.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  const postIngdredient = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData(event.target as HTMLFormElement);
    axios
      .post(`/users/${user?.username}/ingredients/add`, {
        quantity: {
          unit: formData.get('unit'),
          amount: parseFloat(formData.get('quantity') as string),
        },
        name: formData.get('name'),
      })
      .then((response) => {
        updateUser(response.data);
      });
  };

  return (
    <form className="addIngredientMenu" onSubmit={postIngdredient}>
      <input
        className="addIngredientElement"
        placeholder="Name"
        type="text"
        name="name"
        required
      />
      <input
        className="addIngredientElement"
        placeholder="Quantity"
        type="number"
        name="quantity"
        required
      />
      <select
        className="addIngredientElement"
        defaultValue=""
        name="unit"
        required
      >
        <option value="" disabled>
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
        {user?.ingredientContainer?.ingredients?.map(
          (ing: Ingredient, index: number) => {
            return <Item key={index} item={ing} />;
          }
        )}
      </div>
    </div>
  );
}

export default FridgePage;
