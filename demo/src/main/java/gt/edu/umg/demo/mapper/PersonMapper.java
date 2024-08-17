package gt.edu.umg.demo.mapper;

import gt.edu.umg.demo.dto.request.PersonRequest;
import gt.edu.umg.demo.model.PersonModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;

    public PersonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonModel toModel(PersonRequest personRequest) {
        return modelMapper.map(personRequest, PersonModel.class);
    }

}
