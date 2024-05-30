package es.rest.tarea.models.dto;


import es.rest.tarea.models.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    private int price;
    private String type;

    public static PhoneDto convertToDto(Phone phone) {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setType(phone.getType());
        phoneDto.setPrice(phone.getPrice());
        // Otros campos según sea necesario
        return phoneDto;
    }
}
