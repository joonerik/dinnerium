import React, { FC, useContext } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import axios from 'axios';

import './FridgePage.scss';
import { UserContext } from '../../components/UserContext/UserContext';
import AddIngredient from '../../components/AddIngredient/AddIngredient';

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

  const postIngdredient = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form: HTMLFormElement = event.target as HTMLFormElement;
    const formData = new FormData(form);
    form.reset();
    axios
      .post(`/users/${user?.username}/ingredients/add`, {
        quantity: {
          unit: formData.get('unit'),
          amount: parseFloat(formData.get('quantity') as string),
        },
        name: formData.get('ingredient'),
      })
      .then((response) => {
        updateUser(response.data);
        toast.success('Added new ingredient to fridge');
      })
      .catch(() => {
        toast.error('Invalid ingredient name!');
      });
  };

  return (
    <div className="fridgePage__addIngredient">
      <AddIngredient submitIngredientForm={postIngdredient} />;
    </div>
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
      <ToastContainer
        containerId="toastContainer"
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </div>
  );
}

export default FridgePage;
