import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     String [] infoUser = userInfo();
     try {
        String surname = infoUser[0];
        String name = infoUser[1];
        String patronymic = infoUser[2];
        LocalDate birthdate = parseData(infoUser[3]);
        long phoneNumber = parsePhoneNumber(infoUser[4]);
        char gender = parseGender(infoUser[5]);
        dataWriteInFile(surname, name, patronymic, birthdate,
                phoneNumber, gender);
     } catch (IllegalArgumentException e){
         System.out.println("Error " + e.getMessage());
     } catch (DateTimeParseException e){
         System.out.println("Error, incorrect dataFormat. Please use dd.mm.yyyy.");
     }


    }
    public static String[] userInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your last name first name patronymic date of birth phone number and gender separated by a space: ");
        String s = scan.nextLine();
        String[] result = s.split(" ");
        if (result.length!=6){
            throw new IllegalArgumentException("Not all data has been entered");
        }
        return result;
    }

    public static LocalDate parseData(String birthdate){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(birthdate, format);
    }

    public static long parsePhoneNumber(String number){
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("incorrect phone number");
        }
    }
    public  static char parseGender(String gender){
        if (gender.length() != 1 ||
                !(gender.equalsIgnoreCase("f"))||
                (gender.equalsIgnoreCase("m"))){
            throw new IllegalArgumentException("The gender was entered incorrectly.");
        }
        return gender.toLowerCase().charAt(0);
    }
    public static void dataWriteInFile(String surname, String name, String patronymic, LocalDate birthdate, long number, char gender){

            String fileName = surname + ".txt";
            String userinfo = String.format("%s %s %s %s %s %s", surname, name, patronymic, birthdate, number, gender);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(userinfo);
                writer.newLine();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    }



