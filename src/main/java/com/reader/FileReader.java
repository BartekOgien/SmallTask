package com.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final LocalDate CURRENT_TIME = LocalDate.now();
    private static final String WITHOUT_PHONE_NUMBER = "not have a phone";

    public void readFile(String filePath) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());
        Path path = Paths.get(file.getPath());
        try {
            printInfo(createUserList(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> createUserList(Path path) throws IOException {

        Stream<String> fileLines = Files.lines(path);
        List<String> StringOfUsers = fileLines.collect(Collectors.toList());
        List<User> listOfUsers = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String userAsString : StringOfUsers) {
            String[] users = userAsString.split(",");
            String phoneNumber = WITHOUT_PHONE_NUMBER;

            if (users.length > 3) {

                if (!users[3].equals("")) {
                    phoneNumber = users[3];
                }
            }

            LocalDate userDateOfBirth = LocalDate.parse(users[2], formatter);
            listOfUsers.add(new User(users[0], users[1], userDateOfBirth, phoneNumber));
        }
        return listOfUsers;
    }

    public void printInfo(List<User> userList) {

        OptionalInt highestAgeOptional = userList.stream().filter(u -> !u.getPhoneNumber().equals(WITHOUT_PHONE_NUMBER))
                .mapToInt( u -> calculateAgeInDays(u.getDateOfBirth()))
                .max();

        int highestAge = highestAgeOptional.getAsInt();
        System.out.println("File contains: " + userList.size() + " users");
        System.out.println("Oldest user with phone number:");

        userList.stream().filter(u -> !u.getPhoneNumber()
                .equals("not have a phone"))
                .filter(u -> calculateAgeInDays(u.getDateOfBirth()) == highestAge)
                .forEach(user -> System.out.println("Name: " + user.getName() + " /surname: " + user.getSurname()
                        + " /age: " + calculateAgeInYears(user.getDateOfBirth()) + " /phoneNumber: " + user.getPhoneNumber()));
    }


    public int calculateAgeInDays(LocalDate dateOfBirth) {
        return (int)ChronoUnit.DAYS.between(dateOfBirth, CURRENT_TIME);
    }

    public int calculateAgeInYears(LocalDate dateOfBirth) {
        return (int)ChronoUnit.YEARS.between(dateOfBirth, CURRENT_TIME);
    }
}
