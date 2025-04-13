//import { verify } from "jsonwebtoken";
import Cookies from "universal-cookie";

const profile_fetch = (setUser, rol) => {
  const cookies = new Cookies();
  const accessToken = cookies.get("accessToken");
  console.log(accessToken);

  fetch(`http://localhost:8083/api/users/findById`, {
    method: "GET",
    headers: { "Content-Type": "application/json", authorization: accessToken },
  })
  .then(function (response) {
    return response.json();
  })
  .then(function (response) {
    console.log("http://localhost:3030/users/findById");
    console.log(response.body);
    console.log(rol);
    setUser(response.body);
  })
    .catch(function (error) {
      //alert(error);
      console.log(error); // http://localhost:8083/api/users/findById
    });
};

export default profile_fetch;
