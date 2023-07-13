package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class MenuGeneric<T> {

    protected String title;
    protected ArrayList<T> options;
    static Scanner sc = new Scanner(System.in);

    public MenuGeneric() {
    }

    public MenuGeneric(String title, String[] option) {
        this.title = title;
        this.options = new ArrayList<>();
        for (String s : option) {
            options.add((T) s);
        };

    }

    public void display() {
        System.out.println("=========== " + title + " ===========");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));

        }
        System.out.println("=========== END ===========");
    }

    public int getSelected() {
        while(true){
            display();
            System.out.println("Selete your choice :");
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input ! Please re-enter");
                continue;
            }
            return choice;

        }

    }
    public void run(){
        while(true){
            int choice = getSelected();
            if(choice<=0 || choice> options.size()){
                System.out.println("You must choice from 1 to "+options.size());
                continue;
            }
            boolean isExit = execute(choice);
            if(isExit){
                break;
            }
        }
    }
    public abstract boolean execute(int choice);

}