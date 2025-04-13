import Cookies from "universal-cookie";

const token_fetch = () => {
  const cookies = new Cookies();
  //const accessToken = cookies.get("accessToken");
  const refreshToken = cookies.get("refreshToken");
  console.log("REFRESHTOKEN");
  console.log(refreshToken);
  fetch(`http://localhost:8083/api/users/refresh`, {
    method: "POST",
    headers: { "Content-Type": "application/json", Token: refreshToken},
    body: JSON.stringify({ refreshToken }, null, 2),
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (response) {
      console.log("RESPONSE REFRESHTOKEN");
      console.log(response);
      cookies.set("accessToken", response.accessToken, {
        path: "/",
      });
      cookies.set("refreshToken", response.refreshToken, {
        path: "/",
      });
      alert("Токен успешно обновлен");
    })
    .catch(function (error) {
      //alert(error);
      console.log(error);
    });
};

export default token_fetch;
