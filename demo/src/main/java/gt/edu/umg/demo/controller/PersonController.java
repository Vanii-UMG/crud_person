package gt.edu.umg.demo.controller;

import gt.edu.umg.demo.dto.request.PersonRequest;
import gt.edu.umg.demo.model.PersonModel;
import gt.edu.umg.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAll() {
        return personService.getAllPersons();
    }

    @PostMapping
    public String savePerson(@RequestBody PersonRequest personRequest){
        return  personService.savePerson(personRequest);
    }

    @PutMapping("/{idPerson}")
    public String updatePerson(@PathVariable UUID idPerson, @RequestBody PersonRequest personRequest){
        return personService.editPerson(idPerson, personRequest);
    }

    @DeleteMapping("/{idPerson}")
    public boolean deletePerson(@PathVariable UUID idPerson){
        return personService.deletePerson(idPerson);
    }
}
