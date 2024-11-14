package JSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class jsonDeserialization {

    public static class Cat {
        public String name;
        @JsonDeserialize(as = LinkedList.class)
        public List<Cat> cats;
    }

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new FileReader(new File(fileName)), clazz);
    }

    public static <T> T convertFromJsonToNormal(String str, Class<T> clazz, boolean isString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, clazz);
    }

    public static void main(String[] args) throws IOException {
        String jsonString = "{\"name\":\"Murka\",\"cats\":[{\"name\":\"Timka\"},{\"name\":\"Tishka\"}]}";

        Cat cat = convertFromJsonToNormal(jsonString, Cat.class, true);
        System.out.println(cat.name);
        cat.cats.forEach(item -> System.out.println(item.name));
    }
}
