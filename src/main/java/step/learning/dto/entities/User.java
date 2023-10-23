package step.learning.dto.entities;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String salt;
    private String passDk; // RFC 2898 DK - derived key
    private String email;
    private Date birthdate;
    private String avatar;
}
