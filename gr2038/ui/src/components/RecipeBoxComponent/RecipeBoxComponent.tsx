import React, { useState } from 'react';
import axios from 'axios';
import data from '../../../../core/src/main/resources/dinnerium/storage/data.json';

const RecipeBoxComponent = () => {
  const [isUser, setUser] = useState({});
  axios
    .get('../../../../core/src/main/resources/dinnerium/storage/data.json')
    .then((res) => {
      const person = res.data;
      setUser(person);
    });
  console.log(isUser);
  return (
    <div>
      <h2>hei</h2>
    </div>
  );
};

export default RecipeBoxComponent;
