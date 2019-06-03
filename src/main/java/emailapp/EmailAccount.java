package emailapp;

import java.util.Random;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.generate;

public class EmailAccount {

    private enum Department {
        SALES, DEV, ACCT
    }

    private static final int DEFAULT_PASSWORD_LENGTH = 10;
    private static final String COMPANY_SUFFIX = "anyCompany.com";
    private static final int MAIL_BOX_CAPACITY = 500;
    private static final String AVAILABLE_CHARACTERS = "ABSSCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$";

    private String firstName;
    private String lastName;
    private String password;
    private String privateEmail;
    private String companyEmail;
    private Department department;

    EmailAccount(String firstName, String lastName, int department, String privateEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = generatePassword();
        this.privateEmail = privateEmail;
        this.department = Department.values()[department-1];
        this.companyEmail = format("%s.%s@%s.%s", firstName, lastName, this.department.name(), COMPANY_SUFFIX).toLowerCase();
    }

    private String generatePassword() {
        return generate(() -> new Random().nextInt(AVAILABLE_CHARACTERS.length()))
                .limit(DEFAULT_PASSWORD_LENGTH)
                .mapToObj(AVAILABLE_CHARACTERS::charAt)
                .map(String::valueOf)
                .collect(joining());
    }

    public void changePassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    String getCompanyEmail() {
        return companyEmail;
    }

    @Override
    public String toString() {
        return format("EmailAccount[%nname: %s %s, %npersonal email: %s, %ncompany email: %s, %ndepartment: %s, %npassword: %s, %ncapacity: %sMB]",
                firstName, lastName, privateEmail, companyEmail, department, password, MAIL_BOX_CAPACITY);
    }
}
