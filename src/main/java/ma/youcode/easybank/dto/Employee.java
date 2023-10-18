package ma.youcode.easybank.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Employee extends Person {
    
    private LocalDate recruitmentDate;
    private String email;
    private List<Client> clients;

}