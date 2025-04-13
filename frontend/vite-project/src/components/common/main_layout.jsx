import { Box, Button, Flex, VStack, Text } from "@chakra-ui/react";
import { useNavigate } from "react-router";
import React, { useState, useEffect } from "react";
import Cookies from "universal-cookie";

import tokenFetch from "../user/update_token/fetch";
import getRoleAndId_fetch from "../user/getRoleAndId/fetch";
import Profile from "../user/profile/main";
import { jsx } from "react/jsx-runtime";

const MainLayout = ( {children} ) => {

  const navigate = useNavigate();
  const cookies = new Cookies();

  const [role, setRole] = useState("Prop");

  useEffect(() => {
    const accessToken = cookies.get("accessToken");
    const refreshToken = cookies.get("refreshToken");
  
    // Пользователь гость, если куки отсутствуют или равны "unauthorized"
    const isGuest =
      !accessToken ||
      !refreshToken ||
      accessToken === "unauthorized" ||
      refreshToken === "unauthorized";
  
    if (isGuest) {
      setRole({ order: { role: "Гость" } });
  
      // Устанавливаем куки один раз
      if (!accessToken || !refreshToken) {
        cookies.set("accessToken", "unauthorized", { path: "/" });
        cookies.set("refreshToken", "unauthorized", { path: "/" });
      }
    } else {
      getRoleAndId_fetch(setRole);
    }
  }, []);

  const signOut = () => {
  console.log("HELLO");
  cookies.remove("accessToken", { path: "/" });
  cookies.remove("refreshToken", { path: "/" });
  navigate("/");
  };

  const Auth = (role) => {
    console.log(role + " 00000000000");
    const navigate = useNavigate();

    if (role?.order?.role == "Покупатель")
    {
      const role1 = "Покупатель";
      console.log(role1);

      return (
        <>
          <Profile rol = {3}/>
          <Button
            colorScheme="teal"
            fontSize="md"
            onClick={() => {
              tokenFetch();
              navigate("/staff/listAll");
            }}
            padding={2}
            w={150}
            whiteSpace="pre-wrap"
          >
            Мероприятия
          </Button>
          <Button
            colorScheme="teal"
            fontSize="md"
            onClick={() => navigate("/singin")}
            padding={2}
            w={150}
            whiteSpace="pre-wrap"
          >
            Войти
          </Button>
        </>
      );
    }
    else if (role?.order?.role === "Гость")
    {
        return (
            <>
              <Button
                colorScheme="teal"
                fontSize="md"
                onClick={() => navigate("/singin")}
                padding={2}
                w={150}
                whiteSpace="pre-wrap"
              >
                Войти
              </Button>
            </>
          );
    }
  };


  return (
    <Flex height= "100%">
      <VStack
        spacing={8}
        align="flex-start"
        bg="gray.50"
        p={6}
        w={250}
        h= "100vh"
        overflowY= "scroll"
        overflowX= "hidden"
        zIndex={10}
      >
        <Button
          colorScheme="teal"
          fontSize="md"
          onClick={() => navigate("/mainpage")}
          padding={2}
          w={150}
        >
          На главную
        </Button>
        {Auth(role)}
        <Button
          colorScheme="teal"
          fontSize="md"
          onClick={signOut}
          padding={2}
          w={150}
        >
          Выйти
        </Button>
      </VStack>
      <Box w="100%"   height= "100vh">
          {children}
        </Box>
    </Flex>
  );
};

export default MainLayout;
