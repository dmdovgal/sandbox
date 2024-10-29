package SrtringReaderWriter;

import java.io.*;
import java.nio.charset.Charset;

public class Solution {
    public static void main(String[] args) throws IOException {
        String secretRecipe = "You very important string - на разных языках."; // different languages
        ByteArrayInputStream bais = new ByteArrayInputStream(secretRecipe.getBytes(Charset.forName("UTF-8"))); // encoding is important!!!
        InputStreamReader reader = new InputStreamReader(bais);

        // reader is ready so, let's create writer, read a string and sout it.

        Writer writer = new StringWriter();

        while (reader.ready()) {
            writer.write(reader.read());
        }

        String data = writer.toString();
        System.out.printf("data: %s\n", data);
    }

}
