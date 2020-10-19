import React, { FC } from 'react';

import './FridgePage.scss';

interface IItem {
  item: { name: string };
}

const Item: FC<IItem> = ({ item }) => {
  return <div className="item">{item.name}</div>;
};

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

  return (
    <div className="fridgePage">
      <div className="addIngredientMenu">
        <input
          className="addIngredientElement"
          placeholder="Name"
          type="text"
        />
        <input
          className="addIngredientElement"
          placeholder="Quantity"
          type="number"
        />
        <select
          className="addIngredientElement"
          placeholder="Unit"
          name="Unit"
        ></select>
        <button className="addIngredientElement">Add</button>
      </div>
      <div className="itemList">
        {items.map((item) => (
          <Item item={item} />
        ))}
      </div>
    </div>
  );
}

export default FridgePage;
