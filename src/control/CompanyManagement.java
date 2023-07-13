package control;

import checked_input.Input;
import checked_input.Validator;
import model.Company;
import model.Customer;
import view.MenuGeneric;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.function.Predicate;

public class CompanyManagement {

    Scanner sc = new Scanner(System.in);
    Company c = new Company();

    public void addCustomer() {
//        int id = Input.enterValidInteger("Mời nhập id sản phẩm: ", true, true);
        String id = Input.enterString("id của người dùng", Validator.REGEX_USER_ID, (idStr) -> {
            for (Customer l : Company.customerList) {
                if (l.getId().equals(idStr)) {
                    return true;
                }
            }
            return false;
        });
        String name = Input.enterString("Tên của người dùng",Validator.REGEX_FULL_NAME_VN );
        String phone = Input.enterString("Số điện thoại người dùng ", Validator.REGEX_PHONE_NUMBER);
        Calendar birth = Input.enterDate("Ngày sinh","dd/MM/yyyy");
        Customer pr = new Customer(id, name, phone, birth);
        Company.customerList.add(pr);
    }
    public void display() {
        Company.customerList.forEach(c -> System.out.println(c));
    }

    public ArrayList<Customer> search(Predicate<Customer> p) {
        ArrayList<Customer> cus = new ArrayList<>();
        for (Customer cs : Company.customerList) {
            if (p.test(cs)) {
                cus.add(cs);
            }
        }
        return cus;
    }

    public void displayByName() {
        String name = sc.nextLine();
        ArrayList<Customer> search = search(c -> c.getName().equals(name));
        if (search.isEmpty()) {
            System.out.println("Không tìm thấy tên khách hàng");
        } else {
            search.forEach(c -> System.out.println(c));
        }
    }

    public void displayId() {
        String id = Input.enterString("Id cần tìm",Validator.REGEX_USER_ID);
        ArrayList<Customer> search = search(c -> c.getId().equals(id));
        if (search.isEmpty()) {
            System.out.println("Không tìm thấy tên khách hàng");
        } else {
            search.forEach(c -> System.out.println(c));
        }
    }

    public void displayNum() {
        System.out.println("Nhập mã số đt cần tìm(3 chữ số đầu tiên của số điện thoại)");
        String num = sc.nextLine();
        ArrayList<Customer> search = search(c -> c.getPhone().startsWith(num));
        if (search.isEmpty()) {
            System.out.println("Không tìm thấy dữ liệu cần tìm");
        } else {
            System.out.println(search);
        }
    }

    public void displayBirth() {
        String birth = Input.enterString("Ngày sinh cần tìm(dd/MM/yyyy)");
        ArrayList<Customer> search = search(c -> c.getBirth().contains(birth));
        if (search.isEmpty()) {
            System.out.println("Không tìm thấy dữ liệu cần tìm");
        } else {
            System.out.println(search);
        }
    }

    public void deleteid() {
        String id = Input.enterString("ID delete", Validator.REGEX_USER_ID);
        ArrayList<Customer> delete = c.search((c) -> c.getId().equals(id));
        if (delete.isEmpty()) {
            System.out.println("The Customer not exist");
        } else {
            System.out.println("Do you want Delete?)");

            String a = Input.enterString("(y/n)", Validator.REGEX_QUESTION);
            if(a.equals("y")){
                delete.forEach(p -> Company.customerList.remove(id));
                System.out.println("Detele Customer Success");
            }
        }
    }


    public void customerSearch() {
        c.config();
        String[] op = {"Display Customer by Name", "Display Customer by Id", "Display Customer by birthday", "Display Customer by phone", "Exit"};
        MenuGeneric<String> otherMenu = new MenuGeneric<String>("Customer Search", op) {
            @Override
            public boolean execute(int choice) {
                switch (choice) {
                    case 1:
                        displayByName();
                        break;
                    case 2:
                        displayId();
                        break;
                    case 3:
                        displayBirth();
                        break;
                    case 4:
                        displayNum();
                        break;
                    case 5:
                        return true;
                    default:
                        break;
                }
                return false;
            }
        };
        otherMenu.run();


    }
    public void updateInfo() {
        String id = Input.enterString("Mời nhập id cần update",Validator.REGEX_USER_ID);
        ArrayList<Customer> update = c.search((c) -> c.getId().equals(id));
        if (update.isEmpty()) {
            System.out.println("The Customer not exist");
        } else {
            displayMenuUpdate(update.get(0));
            System.out.println("Detele Customer Success");
        }
    }

    public void displayMenuUpdate(Customer updateCustomer) {
        String[] op = {"Update name", "Update phone", "Update birthday", "Exit"};
        MenuGeneric<String> menuUpdate = new MenuGeneric<String>("Update Menu", op) {
            @Override
            public boolean execute(int choice) {
                switch (choice) {
                    case 1:
                        String name = Input.enterString("Mời nhập tên update ",Validator.REGEX_FULL_NAME_VN);
                        if (c.update(updateCustomer, name, "NAME")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }
                        break;
                    case 2:
                        String phone = Input.enterString("Mời nhập phone mới: ",Validator.REGEX_PHONE_NUMBER);
                        if (c.update(updateCustomer, phone, "PHONE")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }
                        break;
                    case 3:
                        String date = Input.enterDate("Mời nhập ngày sinh mới(dd/MM/yy)");
                        if (c.update(updateCustomer, date, "BIRTHDAY")) {
                            System.out.println("Update success");
                        } else {
                            System.out.println("Update fail");
                        }

                        break;
                    case 4:
                        return true;

                    default:
                        break;

                }
                return false;
            }
        };
        menuUpdate.run();
    }
}
