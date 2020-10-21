import React, { FC }, { useState } from 'react';

import './FridgePage.scss';

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
  return (
    <form className="addIngredientMenu" onSubmit={handleSubmit}>
      <input
        className="addIngredientElement"
        placeholder="Name"
        type="text"
        value="name"
      />
      <input
        className="addIngredientElement"
        placeholder="Quantity"
        type="number"
        value="amount"
      />
      <select
        className="addIngredientElement"
        placeholder="Unit"
        name="Unit"
        value="unit"
      ></select>
      <input type="submit" className="addIngredientElement">
        Add
      </input>
    </form>
  );
}

function FridgePage() {
  const items = [
    {
      quantity: {
        unit: 'stk',
        amount: 2.0,
      },
      name: 'bolle',
    },
    {
      quantity: {
        unit: 'stk',
        amount: 20.0,
      },
      name: 'kanelboller',
    },
    {
      quantity: {
        unit: 'gram',
        amount: 200.0,
      },
      name: 'kaffe',
    },
    {
      quantity: {
        unit: 'dl',
        amount: 40.0,
      },
      name: 'melk',
    },
  ];

  function handleSubmit() {
    let newItem = {
      quantity: {
        unit: { document.getElementByValue("unit").value },
        amount: { document.getElementByValue("amount").value },
      },
      name: { document.getElementByValue("name").value },
    };
    items.push(newItem);
  }

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
