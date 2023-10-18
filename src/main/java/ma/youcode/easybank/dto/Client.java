package ma.youcode.easybank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Client extends Person {
     
    private String address;
    private Employee employee;

}