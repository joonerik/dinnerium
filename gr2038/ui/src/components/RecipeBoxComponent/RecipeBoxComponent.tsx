import React, { useContext, useState } from 'react';
//import data from '../../../../core/src/main/resources/dinnerium/storage/data.json';
//import data from '../data.json';
import axios from 'axios';
import './recipeBox.scss';
import UserContext from '../UserContext/UserContext';

const RecipeBoxComponent = () => {
  //const [isUser, setUser] = useState({});
  const { user } = useContext(UserContext);
  return (
    <div>
      <div className="wrapper">
        <div className="sideBar">
          <h1>Recipes</h1>
          <h1>New recipe</h1>
          <hr></hr>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a
            dui id massa laoreet rutrum. Praesent eget elit maximus, semper dui
            nec, sagittis urna. Ut est arcu, commodo ut lectus vel, fringilla
            pharetra elit. Maecenas auctor elementum nunc, et auctor ante
            tincidunt ac. Morbi luctus euismod ullamcorper. Aliquam malesuada
            consequat dolor, et lobortis purus porttitor et. In et aliquet quam,
            id dictum nibh. Sed ultricies enim a nisi malesuada, eu interdum
            diam facilisis. Suspendisse tincidunt sem quis bibendum rhoncus.
            Donec commodo diam sit amet sapien consectetur tempor. Phasellus
            neque libero, condimentum at euismod hendrerit, mollis id nulla.
            Proin auctor magna eget augue semper auctor. Maecenas ornare ipsum
            sed ornare porta. Cras ultricies massa in ligula semper pharetra.
            Proin ornare lorem egestas, iaculis erat ut, ultricies ligula. Nam
            sit amet purus maximus, laoreet lectus in, interdum metus. Ut ornare
            orci augue, vitae pulvinar diam rhoncus a. Duis aliquam, ipsum sed
            laoreet ultricies, nunc arcu viverra ipsum, quis dictum urna felis
            ac tellus. Quisque sed finibus ligula. Aliquam nec nisi sed sem
            eleifend accumsan sed in erat. Integer nisi diam, auctor non metus
            in, semper tristique felis. Fusce molestie auctor felis pretium
            facilisis. Aliquam tempus vestibulum lectus eu varius. Aliquam a
            tortor cursus, tincidunt diam vehicula, blandit nisl. Nunc eget erat
            quis ante fringilla iaculis at quis nisi. Quisque vel volutpat erat.
            Donec faucibus mauris id urna efficitur pretium. Donec ut elit ac
            libero faucibus tincidunt vel ut ante. Nulla eu egestas orci.
            Praesent accumsan malesuada arcu, id pharetra neque consectetur nec.
            Quisque dignissim nunc accumsan, ornare lacus sed, sollicitudin
            velit. Aliquam cursus, nisi ut rhoncus facilisis, quam diam luctus
            sapien, dignissim maximus ipsum ante vel dui. Suspendisse vel justo
            tincidunt, varius leo eget, venenatis massa. Sed quis luctus mauris.
            Class aptent taciti sociosqu ad litora torquent per conubia nostra,
            per inceptos himenaeos. Pellentesque pretium consequat malesuada.
            Integer tristique pellentesque augue sed auctor. Sed quis ex et odio
            fringilla pulvinar. Sed scelerisque, sem non sagittis aliquet, magna
            ipsum pretium neque, sed mollis turpis risus ac sem. Morbi consequat
            ornare est. Aliquam ultrices sapien sit amet feugiat fermentum.
            Morbi rhoncus enim at arcu rhoncus, lobortis tempus ipsum venenatis.
            Sed varius sodales eros blandit tincidunt. Phasellus elementum
            blandit molestie. Nam porttitor suscipit posuere. Nulla convallis eu
            nisl id sagittis. In hac habitasse platea dictumst. Proin ex lorem,
            pulvinar non elit eget, porta maximus ex. Nunc elementum felis a
            arcu eleifend rhoncus. Sed molestie lacus at mollis rutrum. Aenean
            et enim metus. Nullam id finibus eros. Maecenas vestibulum nec est
            quis mollis. Donec sapien diam, vehicula vitae laoreet a, fringilla
            vitae libero. Vestibulum consectetur at est et laoreet. Maecenas
            lacinia et tortor eget pulvinar. Curabitur iaculis aliquam elit eget
            vestibulum. Proin nec pulvinar eros, nec posuere dolor. Fusce quis
            venenatis est. Quisque venenatis, tellus sit amet venenatis aliquet,
            justo ex auctor magna, eu pulvinar sem nunc et mauris. Pellentesque
            ut laoreet urna, at scelerisque ante. In faucibus risus in volutpat
            faucibus. Aliquam eros nunc, fringilla ac urna ornare, malesuada
            feugiat nunc.
          </p>
        </div>
        {user.recipeContainer.recipes.map((item: Recipe) => (
          <div className="recipeBox">
            <div className="recipeBoxDescription">
              <h1>{item.metadata.recipeName}</h1>
              <img src={item.metadata.image} alt="lul"></img>
              <p>Something missing bro</p>
              <p>{item.metadata.minutes} minutes</p>
              <p>
                <img
                  id="portionsIcon"
                  src="https://cdn4.iconfinder.com/data/icons/election-and-campaign/128/2-512.png"
                  alt="l"
                ></img>{' '}
                {item.metadata.portion}
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RecipeBoxComponent;
