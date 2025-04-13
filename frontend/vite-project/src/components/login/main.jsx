import { Formik, Field } from "formik";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  FormErrorMessage,
  Input,
  VStack,
} from "@chakra-ui/react";
import { useNavigate } from "react-router-dom";
import log_fetch from "./fetch";

export default function Login() {
  const navigate = useNavigate();
  return (
    <Flex bg="gray.100" align="center" justify="center" h={800}>
      <Box bg="white" p={6} rounded="md" w={400}>
        <Formik
          initialValues={{
            name: "",
            password: "",
          }}
          onSubmit={(values) => {
            console.log(JSON.stringify(values, null, 2));
            log_fetch(values, navigate);
          }}
        >
          {({ handleSubmit, errors, touched }) => (
            <form onSubmit={handleSubmit}>
              <VStack spacing={6} align="flex-start">
                <FormControl>
                  <FormLabel htmlFor="name">Имя</FormLabel>
                  <Field
                    as={Input}
                    id="name"
                    name="name"
                    type="text"
                    variant="filled"
                  />
                </FormControl>
                <FormControl isInvalid={!!errors.password && touched.password}>
                  <FormLabel htmlFor="password">Пароль</FormLabel>
                  <Field
                    as={Input}
                    id="password"
                    name="password"
                    type="password"
                    variant="filled"
                    validate={(value) => {
                      let error;

                      if (value.length < 2) {
                        error = "Password must contain at least 4 characters";
                      }

                      return error;
                    }}
                  />
                  <FormErrorMessage>{errors.password}</FormErrorMessage>
                </FormControl>

                <Button type="submit" colorScheme="teal" width="full">
                  Войти
                </Button>
              </VStack>
            </form>
          )}
        </Formik>
        <Button
          mt={8}
          colorScheme="teal"
          width="full"
          onClick={() => navigate("/singup")}
        >
          Зарегистрироваться
        </Button>
      </Box>
    </Flex>
  );
}
