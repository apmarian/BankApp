package core.controllers;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.models.User;
import java.util.ArrayList;

public class UserController {
    
    private static ArrayList<User> users = new ArrayList<>();

    public static Response registerUser(String id, String firstname, String lastname, String age) {
        try {

            int idInt;
            try {
                idInt = Integer.parseInt(id);       // convierto el id a un numero entero
            } catch (NumberFormatException ex) {
                return new Response("ID must be numeric.", Status.BAD_REQUEST);
            }

            for (User user : users) {
                if (user.getId() == idInt) {        // compruebo si el ID ya existe
                    return new Response("ID must be unique. This ID is already registered.", Status.BAD_REQUEST);
                }
            }
            
            if (id.length() > 9) {      // valido que el id no tenga más de 9 dígitos
                return new Response("ID must not have more than 9 digits.", Status.BAD_REQUEST);
            }

            if (idInt < 0) {        // valido que el id sea mayor o igual a 0
                return new Response("ID must be greater than or equal to 0.", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);     // verifico si la caja de first name no este vacia
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);      // verifico si  la caja de last name no este vacia
            }

            int ageInt;
            try {
                ageInt = Integer.parseInt(age);     // convierto la edad a un numero entero
            } catch (NumberFormatException ex) {
                return new Response("Age must be a valid number.", Status.BAD_REQUEST);
            }

            if (ageInt < 18) {      // valido que la edad sea mayor o igual a 18
                return new Response("Age must be greater than or equal to 18.", Status.BAD_REQUEST);
            }

            return new Response("User registered successfully.", Status.OK);        // si todo es válido, lo retorna

        } catch (Exception ex) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
