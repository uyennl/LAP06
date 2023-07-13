package view;

import control.CompanyManagement;
import model.Company;

public class Menu {
    static CompanyManagement cm = new CompanyManagement();
    static Company c = new Company();

    public static void displayMenu() {
        c.config();
        String[] op = {"List All Customer", "Search Customer", "Add New Customer", "Delete Customer", "Save", "Update Customer", "Exit"};
        MenuGeneric<String> mainMenu = new MenuGeneric<String>("Customer Manager", op) {
            @Override
            public boolean execute(int choice) {
                switch (choice) {
                    case 1:
                        cm.display();
                        break;
                    case 2:
                        cm.customerSearch();
                        break;
                    case 3:
                        cm.addCustomer();
                        break;
                    case 4:
                        cm.deleteid();
                        break;
                    case 5:
                        if(c.saveFile()){
                            System.out.println("Lưu thành công");
                        }
                        else
                        {
                            System.out.println("Lỗi rồi.Cái này em không fix đc cô fix hộ em với ạ!");
                        }
                        break;
                    case 6:
                        cm.updateInfo();
                        break;
                    case 7:
                        return true;

                    default:
                        break;
                }
                return false;
            }
        };
        mainMenu.run();
    }

}