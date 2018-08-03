package jackpoly;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.apache.commons.io.FileUtils;

import static jackson.Cage.rcePayload;
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
