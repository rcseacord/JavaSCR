// The MIT License (MIT)
//
// Copyright (c) 2022 Robert C. Seacord
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package jackpoly;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Polymorphism {

  private static Object deserialize(ObjectMapper mapper, File f) throws IOException {
    mapper.enableDefaultTyping();
    return mapper.readValue(FileUtils.readFileToByteArray(f), Object.class);
  }

  public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, CannotCompileException, NotFoundException, NoSuchMethodException, ClassNotFoundException {
    // let start creating the zoo
    Zoo zoo = new Zoo("Samba Wild Park", "Paz");
    Lion lion = new Lion("Simba");
    Elephant elephant = new Elephant("Dumbo");
    List<Animal> animals = new ArrayList<>();
    animals.add(lion);
    animals.add(elephant);
    zoo.setAnimals(animals);

    File f = new File("zoo.json");

    // Serialize
    ObjectMapper mapper = new ObjectMapper();
    mapper.writerWithDefaultPrettyPrinter().writeValue(Files.newBufferedWriter(f.toPath(), UTF_8), zoo);

    // Deserialize zoo
    zoo = mapper.readValue(FileUtils.readFileToByteArray(f), Zoo.class);
    System.out.println(zoo);

//    // Deserialize RCE exploit
//    try (PrintWriter out = new PrintWriter("zoo.json", UTF_8.name()))  {
//      out.println(rcePayload());
//    }
//
//    // zoo = mapper.readValue(FileUtils.readFileToByteArray(f), );
//    zoo = (Zoo) deserialize(mapper, f);
//    System.out.println(zoo);
  }
}
