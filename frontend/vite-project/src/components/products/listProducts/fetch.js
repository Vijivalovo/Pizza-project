import Cookies from "universal-cookie";

const get_slots_fetch = (setSlots) => {
  const cookies = new Cookies();
  const accessToken = cookies.get("accessToken");
  fetch(`http://localhost:8082/api/products/getAll/`, {
    method: "GET",
    headers: { "Content-Type": "application/json", Authorization: accessToken },
  })
    .then(function (response) {
      return response.json();
    })
    .then(function (response) {
      console.log(response);
      setSlots(response.body);
    })
    .catch(function (error) {
      alert(error);
      console.log(error);
    });
};

export default get_slots_fetch;
