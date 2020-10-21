import React, { FC } from 'react';

import './FridgePage.scss';
import { useState } from 'react';

interface IItem {
  item: { quantity: { unit: string; amount: number }; name: string };
}

const items = [
  {
    quantity: {
      unit: 'stk',
      amount: 2.0,
    },
    name: 'bolle',
  },
];

const Item: FC<IItem> = ({ item }) => {
  return (
    <div className="item">
      {item.quantity.amount}
      {item.quantity.unit} {item.name}
    </div>
  );
};

function Menu() {
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');
  const [unit, setUnit] = useState('');

  function addItem() {
    let newItem = {
      quantity: {
        unit: unit,
        amount: Number(amount),
      },
      name: name,
    };
    items.push(newItem);
  }

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
      ></select>
      <input type="button" onClick={addItem} className="addIngredientElement">
        Add
      </input>
    </form>
  );
}

function FridgePage() {
  return (
    <div className="fridgePage">
      <Menu />
      <div className="itemList">
        {items.map((item) => (
          <Item item={item} />
        ))}
      </div>
    </div>
  );
}

export default FridgePage;
