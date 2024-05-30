package es.rest.tarea.services;

import es.rest.tarea.models.Phone;
import es.rest.tarea.models.dto.PhoneDto;
import es.rest.tarea.repositories.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static es.rest.tarea.models.dto.PhoneDto.convertToDto;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public List<Phone> getAllPhones() {
        return (List<Phone>) phoneRepository.findAll();
    }

    public Optional<Phone> getPhoneById(int id) {
        return phoneRepository.findById(Long.valueOf(id));
    }

    public PhoneDto addPhone(PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setType(phoneDto.getType());
        phone.setPrice(phoneDto.getPrice());
        phoneRepository.save(phone);
        return phoneDto;

    }
    @Transactional
    public PhoneDto updatePhone(Long  phoneId, PhoneDto phoneDto) {
        Phone phoneToUpdate = phoneRepository.findById(phoneId).orElse(null);

        phoneToUpdate.setType(phoneDto.getType());
        phoneToUpdate.setPrice(phoneDto.getPrice());
        phoneRepository.save(phoneToUpdate);

        Phone updatedPhone = phoneRepository.save(phoneToUpdate);
        return PhoneDto.convertToDto(updatedPhone);
    }

    public void deletePhone(int id) {
        phoneRepository.deleteById(Long.valueOf(id));
    }


    public Phone savePhone(Phone phone) {
        return phoneRepository.save(phone);
    }
}
