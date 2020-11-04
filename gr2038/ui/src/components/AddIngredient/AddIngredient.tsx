import React, { FC, useEffect, useState } from 'react';
import './addIngredient.scss';
import axios from 'axios';

interface IAddIngredient {
  submitIngredientForm: (event: React.FormEvent<HTMLFormElement>) => void;
}

const AddIngredient: FC<IAddIngredient> = ({ submitIngredientForm }) => {
  const [isUnits, setUnits] = useState<string[]>([]);

  useEffect(() => {
    axios.get('/units').then((response) => {
      setUnits(response.data.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  const checkValidity = (event: React.ChangeEvent<HTMLInputElement>) => {
    event.target.setCustomValidity('');
    if (!event.target.validity.valid) {
      event.target.setCustomValidity('Only characters are allowed');
    }
  };

  return (
    <form onSubmit={submitIngredientForm} className="form__ingredient">
      <input
        type="text"
        name="ingredient"
        placeholder="ingredient"
        onChange={(e) => checkValidity(e)}
        required
        pattern="^[ A-Za-z]+$"
      />
      <input type="number" name="quantity" placeholder="Amount" required />
      <select
        defaultValue=""
        name="unit"
        placeholder="unit"
        required
        className="unit-recipe"
      >
        <option value="" disabled>
          Unit
        </option>
        {isUnits.map((item: string, index: number) => {
          return (
            <option key={index} value={item}>
              {item}
            </option>
          );
        })}
      </select>
      <button type="submit" id="addIngredientButton">
        Add
      </button>
    </form>
  );
};

export default AddIngredient;
