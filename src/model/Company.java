package model;

import control.CompanyManagementIO;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;

public class Company {
    public static ArrayList<Customer> customerList = new ArrayList<>();
    public static CompanyManagementIO cp = new CompanyManagementIO();
    private static final String BASE_PATH = new File("").getAbsolutePath();
    private static final String READ_PATH = "\\src\\data\\customer.txt";
    private static final String WRITE_PATH = "\\src\\data\\customer1.txt";

    public void config() {
        try {
            cp.readFile(BASE_PATH + READ_PATH);

        } catch (ParseException e) {
            e.printStackTrace();

        }
    }
    public static Calendar toCalendar(String str) {
        Calendar c = Calendar.getInstance();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try {
            d = f.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(CompanyManagementIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setTime(d);
        return c;
    }

    public boolean saveFile(){

        return cp.savefile(BASE_PATH+WRITE_PATH,customerList,(c)->c.toSaveString());
    }
    public void addCustomer(String id, String name, String phone, Calendar birth) {
        customerList.add(new Customer(id, name, phone, birth));
    }

    public void displayListCustomer() {
        customerList.forEach((t) -> {
            System.out.println(t);
        });
    }

    public ArrayList<Customer> search(Predicate<Customer> p) {
        ArrayList<Customer> rs = new ArrayList<>();
        for (Customer s : customerList) {
            if (p.test(s)) {
                rs.add(s);
            }
        }
        return rs;

    }

    public boolean update(Customer up, String data, String type){
        switch (type) {
            case "NAME":
                up.setName(data);
                return true;
            case "PHONE":
                up.setPhone(data);
                return true;
            case "BIRTHDAY":
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date birthDay = formatter.parse(data);
                    Calendar date = Calendar.getInstance();
                    date.setTime(birthDay);
                    up.setBirth(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return true;

            default:
                return false;

        }
    }


}