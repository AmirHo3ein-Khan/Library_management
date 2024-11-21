package ir.librarymanagement;

import ir.librarymanagement.model.*;
import ir.librarymanagement.model.enums.UserType;
import ir.librarymanagement.ui.Menu;
import ir.librarymanagement.util.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        Menu.startProject();
    }


}
