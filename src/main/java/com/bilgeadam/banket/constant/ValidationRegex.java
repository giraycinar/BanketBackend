package com.bilgeadam.banket.constant;

public abstract class ValidationRegex {

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{8,32}$";

    // Volkan: https://www.baeldung.com/java-validate-uuid-string
    public static final String UUID_REGEX = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";

}
