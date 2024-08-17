package gt.edu.umg.demo.service;

import gt.edu.umg.demo.dto.request.PersonRequest;
import gt.edu.umg.demo.mapper.PersonMapper;
import gt.edu.umg.demo.model.PersonModel;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private final PersonMapper personMapper;
    private final Path path = Paths.get("C:/Users/Alcan/OneDrive/Escritorio/PersonData.txt");

    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public String getAllPersons() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toString(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            return "Error al leer el archivo " + e.getMessage();
        }
    }

    public String savePerson(PersonRequest personRequest){
        PersonModel personModel = personMapper.toModel(personRequest);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(), true))) {
            writer.write("{id: " + personModel.getIdPerson() + ", name: " + personModel.getName() + ", email: " + personModel.getEmail() + "}");
            writer.newLine();
            return "Persona guardada correctamente";
        } catch (IOException e) {
            return "Errro al guardar la persona: " + e.getMessage();
        }

    }

    public String editPerson(UUID idPerson, PersonRequest personRequest){
        try {
            // Leer el archivo y guardar las líneas en una lista
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Modificar la línea si cumple con alguna condición
                    if (line.startsWith("{id: " + idPerson)) {
                        line = "{id: " + idPerson + ", name: " + personRequest.getName() + ", email: " + personRequest.getEmail() + "}";
                    }
                    lines.add(line);
                }
            }

            // Volver a escribir las líneas en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            return  "Persona modificada correctamente.";

        } catch (IOException e) {
            return  "No se logro modificar correctamente a la persona: " + e.getMessage();
        }
    }

    public boolean deletePerson(UUID idPerson){
        try {
            // Leer el archivo y guardar las líneas en una lista
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Omitir la línea que deseas eliminar
                    if (!line.startsWith("{id: " + idPerson)) {  // Esta línea no se guardará, y por lo tanto se eliminará
                        lines.add(line);
                    }
                }
            }

            // Volver a escribir las líneas en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            return true;

        } catch (IOException e) {

            return false;

        }
    }

}
