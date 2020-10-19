import React, { useState } from 'react';

const FetchHello = () => {
  const [textData, setData] = useState('');

  const fetchHello = async () => {
    const response = await fetch('/hello', {
      mode: 'no-cors',
    });
    const data = await response.text();
    console.log(data);
    setData(data);
  };
  fetchHello();
  return <p>{textData}</p>;
};
export default FetchHello;
