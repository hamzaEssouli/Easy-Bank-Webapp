package ma.youcode.easybank.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person {
    
    protected Integer id;
    protected String lastName;
    protected String firstName;
    protected LocalDate dateOfBirth;
    protected String phoneNumber;

}