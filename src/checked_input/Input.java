package checked_input;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class Input {
    private Input() {
    }
    ;
    static Scanner sc = new Scanner(System.in);
    private static final String ERR_MESSAGE = "Wrong Input ! Please Re-enter ";
    private static final String REGEX_NOT_MATCH = "Input Not Valid ! Please Re-enter ";

    public static String enterString(String mess) {
        String str;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                str = sc.nextLine().trim();

            } catch (NoSuchElementException e) {

                System.out.println(ERR_MESSAGE);
                continue;
            }

            return str;

        }
    }

    static public String enterString(String mess, String regex) {
        String str;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                str = sc.nextLine().trim();

            } catch (NoSuchElementException e) {

                System.out.println(ERR_MESSAGE);
                continue;
            }

            boolean isTrue = Validator.checkRegex(str, regex);
            if (isTrue) {
                return str;
            } else {
                System.out.println(REGEX_NOT_MATCH);
            }

        }
    }

    public static String enterDate(String mess) {
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                String dateStr = sc.nextLine().trim();
                if (Validator.isValidDate(dateStr)) {
                    return dateStr;
                }
            } catch (NoSuchElementException e) {
                System.out.println(ERR_MESSAGE);
                continue;

            }
        }
    }

    static public String enterString(String mess, String regex, Predicate<String> duplicate) {
        String str;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                str = sc.nextLine().trim();

            } catch (NoSuchElementException e) {

                System.out.println(ERR_MESSAGE);
                continue;
            }

            boolean isTrue = Validator.checkRegex(str, regex);
            if (isTrue) {
                if (!duplicate.test(str)) {
                    return str;
                } else {
                    System.out.println(mess + " has exist");
                }
            } else {
                System.out.println(REGEX_NOT_MATCH);
            }

        }
    }

    public static int enterInt(String mess, boolean check, Predicate<Integer> duplicate) {
        int intVar;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                intVar = Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println(ERR_MESSAGE);
                continue;
            }
            if (check) {
                if (intVar < 0) {
                    System.out.println(mess + " must greater than 0");
                } else {
                    if (duplicate.test(intVar)) {
                        System.out.println(mess + " has exist");
                    } else {
                        return intVar;

                    }
                }
            } else {
                return intVar;
            }
        }
    }

    public static int enterInt(String mess, boolean check) {
        int intVar;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                intVar = Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println(ERR_MESSAGE);
                continue;
            }
            if (check) {
                if (intVar < 0) {
                    System.out.println(mess + " must greater than 0");
                } else {

                    return intVar;

                }
            } else {
                return intVar;
            }
        }
    }

    public static float enterFloat(String Mess, boolean check) {
        float floatVar;
        while (true) {
            System.out.println("Enter " + Mess + " :");
            try {
                floatVar = Float.parseFloat(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println(ERR_MESSAGE);
                continue;
            }
            if (check) {
                if (floatVar < 0) {
                    System.out.println(Mess + " must greater than 0");
                } else {
                    return floatVar;
                }
            } else {
                return floatVar;
            }
        }
    }

    public static Calendar enterDate(String mess, String pattern) {
        Calendar result = Calendar.getInstance();
        while (true) {
            System.out.println("Enter " + mess + ":");
            try {
                String dateStr = sc.nextLine().trim();
                if (!Validator.isValidDate(dateStr)) {
                    throw new ParseException("Invalid Date Format", 0);
                }

                DateFormat formatter = new SimpleDateFormat(pattern);
                formatter.setLenient(false);
//                Date birthDay = formatter.parse(dateStr);
                Date birthDay = formatter.parse(dateStr);
                result.setTime(birthDay);
            } catch (ParseException e) {
                System.out.println(REGEX_NOT_MATCH);
                continue;
            } catch (NoSuchElementException e) {
                System.out.println(ERR_MESSAGE);
                continue;
            }
            return result;
        }
    }
    public static long enterLong(String mess, boolean check) {
        long longVar;
        while (true) {
            System.out.println("Enter " + mess + " :");
            try {
                longVar = Long.parseLong(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println(ERR_MESSAGE);
                continue;
            }
            if (check) {
                if (longVar < 0) {
                    System.out.println(mess + " must greater than 0");
                } else {

                    return longVar;

                }
            } else {
                return longVar;
            }
        }
    }
}

