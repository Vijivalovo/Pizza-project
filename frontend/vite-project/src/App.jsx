import MainLayout from "./components/common/main_layout";
import Slots from "./components/products/listProducts/main";
import Registration from "./components/registration/main";
import Login from "./components/login/main";
import { useRoutes } from "react-router-dom";
import './App.css';

function App() {
  const routes = useRoutes([
    {
      path: "*",
      element: (
        <MainLayout>
          <Slots />
        </MainLayout>
      ),
    },
    {
      path: "/singup",
      element: (
        <MainLayout>
          <Registration />
        </MainLayout>
      ),
    },
    {
      path: "/singin",
      element: (
        <MainLayout>
          <Login />
        </MainLayout>
      ),
    },
  ]);

  return routes;
}

export default App
