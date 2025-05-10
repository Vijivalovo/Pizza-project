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
import reg_fetch from "./fetch";

export default function Registration() {
  const navigate = useNavigate();
  return (
    <Flex bg="gray.100" align="center" justify="center" h={800}>
      <Box bg="white" p={6} rounded="md" w={400}>
        <Formik
          initialValues={{
            name: "",
            numberPhone: "",
            password: "",
          }}
          onSubmit={(values) => {
            console.log(JSON.stringify(values, null, 2));
            reg_fetch(values, navigate);
          }}
        >
          {({ handleSubmit, errors, touched }) => (
            <form onSubmit={handleSubmit}>
              <VStack spacing={7} align="flex-start">
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
                <FormControl>
                <FormLabel htmlFor="numberPhone">Номер телефона</FormLabel>
                <Field name="numberPhone">
                    {({ field, form }) => (
                    <Input
                        {...field}
                        id="numberPhone"
                        type="text"
                        variant="filled"
                        value={field.value ? field.value : '+375'}
                        onChange={(e) => {
                        const value = e.target.value;
                        
                        const onlyDigits = value.replace(/\D/g, '');
                        
                        if (onlyDigits.length <= 9) {
                            form.setFieldValue("numberPhone", `+375${onlyDigits}`);
                        }
                        }}
                        maxLength="12"
                    />
                    )}
                </Field>
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

                      if (value.length < 4) {
                        error = "Password must contain at least 4 characters";
                      }

                      return error;
                    }}
                  />
                  <FormErrorMessage>{errors.password}</FormErrorMessage>
                </FormControl>

                <Button type="submit" colorScheme="teal" width="full">
                  Зарегистрироваться
                </Button>
              </VStack>
            </form>
          )}
        </Formik>
      </Box>
    </Flex>
  );
}