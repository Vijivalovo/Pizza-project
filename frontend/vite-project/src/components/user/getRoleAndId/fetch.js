import Cookies from "universal-cookie";

const getRoleAndId_fetch = (setRoleAndId) => {
    const cookies = new Cookies();
  const accessToken = cookies.get("accessToken");
  fetch(`http://localhost:8083/api/users/validateForData`, {
    method: "GET",
    headers: { "Content-Type": "application/json", authorization: accessToken},
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (response) {
        console.log(response);
        setRoleAndId(response);
    })
    .catch(function (error) {
      alert(error);
      //console.log(error);
    });
};

export default getRoleAndId_fetch;
