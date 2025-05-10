import Cookies from "universal-cookie";
const log_fetch = (values, navigate) => {
  const cookies = new Cookies();

  fetch("http://localhost:8083/api/users/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(values, null, 2),
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (response) {
      console.log("login");
      //window.location.reload();
      console.log(response);
      cookies.set("accessToken", response.order.tokens.accessToken, {
        path: "/",
      });
      cookies.set("refreshToken", response.order.tokens.refreshToken, {
        path: "/",
      });
      navigate("/");
    })
    .catch(function (error) {
      alert(error);
      //console.log(error);
    });
};

export default log_fetch;