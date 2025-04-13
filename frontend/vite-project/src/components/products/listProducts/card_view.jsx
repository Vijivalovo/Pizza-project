import {
    Flex,
    Text,
    Button,
    Card,
    CardHeader,
    CardBody,
    Heading,
    Popover,
    PopoverTrigger,
    PopoverArrow,
    PopoverContent,
    PopoverCloseButton,
    PopoverBody,
  } from "@chakra-ui/react";
  import { useNavigate } from "react-router-dom";
  
  export default function CardView({
    id,
    name,
    weight,
    size
  }) {
    const navigate = useNavigate();
      return (
        <Flex
          bg="white"
          flexDirection="column"
          alignItems="center"
          rounded="md"
          mr={10}
          ml={10}
          p={6}
          mt={10}
          w="100%"
          h="auto"
          maxWidth={340}
          maxHeight={420}
        >
          <Card
            bg="gray.100"
            p={4}
            roundedTop="md"
            mb={4}
            w="100%"
            h="auto"
            maxHeight={450}
          >
            <CardHeader>
              <Heading size="lg">{id}</Heading>
            </CardHeader>
            <CardBody>
              <Text size="md">Название</Text>
              <Heading size="md" mb={1}>
                {name}
              </Heading>
              <Text size="md">Вес</Text>
              <Heading size="md" mb={1}>
                {weight}
              </Heading>
              <Text size="md">Размер</Text>
              <Heading size="md" mb={1}>
                {size}
              </Heading>
              <Popover>
                
                  <Button
                    m={0}
                    p={15}
                    b={0}
                    size="md"
                    outline="none"
                    style={{marginTop: 25}}
                    colorScheme="teal"
                    onClick={() => {
                      let value = id;
                      console.log(value);
                      deleteFaculty_fetch(value);
                      window.location.reload();
                    }}
                  >
                    Удалить
                  </Button>
                <PopoverContent>
                  <PopoverArrow />
                  <PopoverCloseButton />
                  <PopoverBody p={5}>{id}</PopoverBody>
                </PopoverContent>
              </Popover>
            </CardBody>
          </Card>
        </Flex>
      );
}
  