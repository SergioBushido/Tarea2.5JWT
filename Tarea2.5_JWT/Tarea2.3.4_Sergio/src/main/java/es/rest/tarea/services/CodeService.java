package es.rest.tarea.services;


import es.rest.tarea.models.Code;
import es.rest.tarea.models.dto.CodeDto;
import es.rest.tarea.repositories.CodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CodeService {

    private CodeRepository codeRepository;

    public List<Code> findAll() {
        return (List<Code>) codeRepository.findAll();
    }

    public Optional<Code> findById(Long id) {
        return codeRepository.findById(id);
    }

    public Code save(Code code) {
        return codeRepository.save(code);
    }

    public boolean delete(Long id) {
        if(codeRepository.existsById(id)) {
            codeRepository.deleteById(id);
            return true;

        }else {
            return false;
        }
    }

    //mirar el de phone
    public Code update(Long id, CodeDto codeDto) {
        Code code = codeRepository.findById(id).orElse(null);
        if (code == null) {
            // Manejar el caso cuando no se encuentra el objeto, por ejemplo, lanzar una excepción
            throw new ResourceNotFoundException("Code not found with id: " + id);
        }
        code.setPackageName(codeDto.getPackageName());
        code.setFile(codeDto.getFile());
        codeRepository.save(code);

        return code;
    }


    public Code add(CodeDto codeDto) {
        Code code = new Code();
        code.setPackageName(codeDto.getPackageName());
        code.setFile(codeDto.getFile());
        codeRepository.save(code);
        return code;
    }
}
