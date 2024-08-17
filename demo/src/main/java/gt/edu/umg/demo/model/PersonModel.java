package gt.edu.umg.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Getter
public class PersonModel {
    private final UUID idPerson = UUID.randomUUID();
    private String name;
    private String email;
}
