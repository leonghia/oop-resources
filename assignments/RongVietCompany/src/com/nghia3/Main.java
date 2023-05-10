package com.nghia3;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Company company = new Company("Company", "Vietnam");

    public static void main(String[] args) {


        boolean isExit = false;
        while (!isExit) {
            System.out.println();
            System.out.println("=".repeat(15) + " Welcome to " + company.getName() + " " + "=".repeat(15));
            System.out.println("""
                    1. Input and output personnel
                    2. Delete and update personnel
                    3. Find personnel by salary range
                    4. Sort personnel by name and income
                    5. Output top 5 personnel with highest income
                    6. Exit""");
            System.out.print("Enter your selection [1 – 6]: ");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> {
                    inputAndOutputPersonnel();
                }
                case "2" -> {
                    deleteAndUpdatePersonnel();
                }
                case "3" -> {
                    findPersonnelBySalaryRange();
                }
                case "4" -> {
                    sortPersonnelByNameOrIncome();
                }
                case "5" -> {
                    outputTop5PersonnelWithHighestIncome();
                }
                case "6" -> {
                    isExit = true;
                }
            }
        }
    }

    private static void inputAndOutputPersonnel() {
        boolean isBack = false;
        while (!isBack) {
            System.out.println();
            System.out.println("-".repeat(15) + " Input and output personnel " + "-".repeat(15));
            System.out.println("""
                    (I)nput new personnel
                    (O)ut all personnel
                    (B)ack to main menu""");
            System.out.print("Enter your selection: ");
            String selection = scanner.nextLine();
            switch (selection.toUpperCase()) {
                case "I" -> {
                    String name, phone, email;
                    name = phone = email = null;
                    double baseSalary = 0.0;
                    Personnel personnel = null;
                    System.out.println("-".repeat(15) + " Input new personnel " + "-".repeat(15));
                    boolean isTypeOfPersonnelValid = false;
                    String typeOfPersonnel = null;
                    while (!isTypeOfPersonnelValid) {
                        System.out.print("What type of personnel (\"A\" for administrative staff, \"M\" for manager, \"S\" for sales staff): ");
                        typeOfPersonnel = scanner.nextLine().trim();
                        if (typeOfPersonnel.equalsIgnoreCase("A") || typeOfPersonnel.equalsIgnoreCase("M") || typeOfPersonnel.equalsIgnoreCase("S")) {
                            isTypeOfPersonnelValid = true;
                        } else {
                            System.out.println("Invalid type of personnel, please enter again !");
                        }
                    }
                    boolean isNameValid = false;
                    while (!isNameValid) {
                        System.out.print("Enter name: ");
                        name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Name must not be empty");
                        } else {
                            isNameValid = true;
                        }
                    }
                    boolean isPhoneValid = false;
                    while (!isPhoneValid) {
                        System.out.print("Enter phone: ");
                        phone = scanner.nextLine().trim();
                        if (phone.isEmpty()) {
                            System.out.println("Phone must not be empty");
                        } else {
                            isPhoneValid = true;
                        }
                    }
                    boolean isEmailValid = false;
                    while (!isEmailValid) {
                        System.out.print("Enter email: ");
                        email = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("Email must not be empty");
                        } else {
                            isEmailValid = true;
                        }
                    }
                    boolean isBaseSalaryValid = false;
                    while (!isBaseSalaryValid) {
                        System.out.print("Enter base salary (VND): ");
                        try {
                            baseSalary = Double.parseDouble(scanner.nextLine());
                            if (baseSalary > 0) {
                                isBaseSalaryValid = true;
                            } else {
                                System.out.println("Base salary must be greater than 0");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid number, please enter again");
                        }
                    }
                    switch (typeOfPersonnel.toUpperCase()) {
                        case "A" -> {
                            personnel = new AdministrativeStaff(name, phone, email, baseSalary);
                        }
                        case "M" -> {
                            double responsibleSalary = 0.0;
                            boolean isResponsibleSalaryValid = false;
                            while (!isResponsibleSalaryValid) {
                                System.out.print("Enter responsible salary (VND): ");
                                try {
                                    responsibleSalary = Double.parseDouble(scanner.nextLine());
                                    if (responsibleSalary > 0) {
                                        isResponsibleSalaryValid = true;
                                    } else {
                                        System.out.println("Responsible salary must be greater than 0");
                                    }
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Invalid number, please enter again");
                                }
                            }
                            personnel = new Manager(name, phone, email, baseSalary, responsibleSalary);
                        }
                        case "S" -> {
                            double sales = 0.0;
                            double commission = 0.0;
                            boolean isSalesValid = false;
                            while (!isSalesValid) {
                                System.out.print("Enter sales: ");
                                try {
                                    sales = Double.parseDouble(scanner.nextLine());
                                    if (sales > 0) {
                                        isSalesValid = true;
                                    } else {
                                        System.out.println("Sales must be greater than 0");
                                    }
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Invalid number, please enter again");
                                }
                            }
                            boolean isCommissionValid = false;
                            while (!isCommissionValid) {
                                System.out.print("Enter commission: ");
                                try {
                                    commission = Double.parseDouble(scanner.nextLine());
                                    if (commission > 0 && commission <= 1) {
                                        isCommissionValid = true;
                                    } else {
                                        System.out.println("Commission must be greater than 0 and less than or equal to 1");
                                    }
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Invalid number, please enter again");
                                }
                            }
                            personnel = new SalesStaff(name, phone, email, baseSalary, sales, commission);
                        }
                    }
                    System.out.println("Created successfully: " + personnel);
                    company.addPersonnel(personnel);
                }
                case "O" -> {
                    System.out.println("-".repeat(15) + " Output all personnel " + "-".repeat(15));
                    System.out.println("Loading data" + ".".repeat(20));

                    Map<Integer, Personnel> personnelMap = company.getPersonnelMap();

                    System.out.println("=".repeat(140));
                    System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
                    System.out.println("=".repeat(140));

                    for (Integer key : personnelMap.keySet()) {
                        Personnel personnel = personnelMap.get(key);
                        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                        System.out.println("-".repeat(140));
                    }
                }
                case "B" -> {
                    isBack = true;
                }
            }
        }
    }

    private static void deleteAndUpdatePersonnel() {
        boolean isBack = false;
        while (!isBack) {
            System.out.println();
            System.out.println("-".repeat(15) + " Delete and update personnel " + "-".repeat(15));
            System.out.println("""
                    (D)elete personnel
                    (U)pdate personnel
                    (B)ack to main menu""");
            System.out.print("Enter your selection: ");
            String selection = scanner.nextLine().trim();

            switch (selection.toUpperCase()) {
                case "U" -> {
                    System.out.println("-".repeat(15) + " Update personnel " + "-".repeat(15));
                    boolean isIDValid = false;
                    int id = 0;
                    while (!isIDValid) {
                        System.out.print("Enter personnel ID: ");
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            isIDValid = true;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid ID, please enter again");
                        }
                    }
                    Personnel personnel = company.findPersonnel(id);
                    if (personnel != null) {
                        System.out.print("Update phone (skip if not needed): ");
                        String phone = scanner.nextLine().trim();
                        if (!phone.isEmpty()) {
                            personnel.setPhone(phone);
                        }
                        System.out.print("Update email (skip if not needed): ");
                        String email = scanner.nextLine().trim();
                        if (!email.isEmpty()) {
                            personnel.setEmail(email);
                        }
                        System.out.print("Update base salary (skip if not needed): ");
                        double baseSalary;
                        try {
                            baseSalary = Double.parseDouble(scanner.nextLine());
                            if (baseSalary > 0) {
                                personnel.setBaseSalary(baseSalary);
                            }
                        } catch (NumberFormatException ignored) {

                        }
                        switch (personnel.getClass().getSimpleName()) {
                            case "Manager" -> {
                                Manager manager = (Manager) company.findPersonnel(id);
                                System.out.print("Update responsible salary (skip if not needed): ");
                                double responsibleSalary;
                                try {
                                    responsibleSalary = Double.parseDouble(scanner.nextLine());
                                    if (responsibleSalary > 0) {
                                        manager.setResponsibleSalary(responsibleSalary);
                                    }
                                } catch (NumberFormatException ignored) {

                                }
                            }
                            case "SalesStaff" -> {
                                SalesStaff salesStaff = (SalesStaff) company.findPersonnel(id);
                                System.out.print("Update sales (skip if not needed): ");
                                double sales;
                                try {
                                    sales = Double.parseDouble(scanner.nextLine());
                                    if (sales > 0) {
                                        salesStaff.setSales(sales);
                                    }
                                } catch (NumberFormatException ignored) {

                                }
                                System.out.print("Update commission (skip if not needed): ");
                                double commission;
                                try {
                                    commission = Double.parseDouble(scanner.nextLine());
                                    if (commission > 0) {
                                        salesStaff.setCommission(commission);
                                    }
                                } catch (NumberFormatException ignored) {

                                }
                            }
                        }
                        System.out.println("Updated successfully: " + personnel);

                    } else {
                        System.out.println("Sorry, personnel ID is not found !");
                    }

                }
                case "D" -> {
                    System.out.println("-".repeat(15) + " Delete personnel " + "-".repeat(15));
                    boolean isIDValid = false;
                    int id = 0;
                    while (!isIDValid) {
                        System.out.print("Enter personnel ID: ");
                        try {
                           id = Integer.parseInt(scanner.nextLine());
                           isIDValid = true;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid ID, please enter again");
                        }
                    }
                    Personnel personnel = company.findPersonnel(id);
                    if (personnel != null) {
                        boolean isDecisionValid = false;
                        while (!isDecisionValid) {
                            System.out.print("Do you really want to delete this personnel? (Y/n): ");
                            selection = scanner.nextLine();
                            if (selection.equalsIgnoreCase("Y")) {
                                System.out.println("Deleted successfully: " + personnel);
                                company.deletePersonnel(id);
                                isDecisionValid = true;
                            } else if (selection.equalsIgnoreCase("N")) {
                                isDecisionValid = true;
                            }
                        }
                    } else {
                        System.out.println("Sorry, personnel ID is not found");
                    }
                }
                case "B" -> {
                    isBack = true;
                }
            }
        }
    }

    private static void findPersonnelBySalaryRange() {
        System.out.println();
        System.out.println("-".repeat(15) + " Find personnel by salary range " + "-".repeat(15));
        boolean isLowerRangeValid = false;
        double lowerRange = 0.0;
        while (!isLowerRangeValid) {
            System.out.print("Enter lower range (VND): ");
            try {
                lowerRange = Double.parseDouble(scanner.nextLine());
                if (lowerRange >= 0) {
                    isLowerRangeValid = true;
                } else {
                    System.out.println("Lower range must be greater than or equal to 0");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number, please enter again");
            }
        }
        boolean isHigherRangeValid = false;
        double higherRange = 0;
        while (!isHigherRangeValid) {
            System.out.print("Enter higher range (VND): ");
            try {
                higherRange = Double.parseDouble(scanner.nextLine());
                if (higherRange > lowerRange) {
                    isHigherRangeValid = true;
                } else {
                    System.out.println("Higher range must be greater than lower range which is " + lowerRange);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid number, please enter again");
            }
        }

        List<Personnel> personnelList = company.findPersonnelBySalaryRange(lowerRange, higherRange);
        if (personnelList.size() > 0) {
            System.out.println("Personnel who salary is from " + lowerRange + " to " + higherRange + ": " + personnelList.size() + " results");
            System.out.println("=".repeat(140));
            System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
            System.out.println("=".repeat(140));

            for (Personnel personnel : personnelList) {
                System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                System.out.println("-".repeat(140));
            }
        } else {
            System.out.println("Personnel who salary is from " + lowerRange + " to " + higherRange + ": 0 result");
        }
    }

    private static void sortPersonnelByNameOrIncome() {
        Map<Integer, Personnel> personnelMap = company.getPersonnelMap();
        List<Personnel> personnelList = new ArrayList<>(personnelMap.size());
        for (Integer key : personnelMap.keySet()) {
            Personnel personnel = personnelMap.get(key);
            personnelList.add(personnel);
        }
        boolean isBack = false;
        while (!isBack) {
            System.out.println();
            System.out.println("-".repeat(15) + " Sort personnel by name or income " + "-".repeat(15));
            System.out.println("""
                    1. Sort personnel by name in ascending order
                    2. Sort personnel by name in descending order
                    3. Sort personnel by income in ascending order
                    4. Sort personnel by income in descending order
                    5. Back""");
            System.out.print("Enter your selection [1 – 5]: ");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> {
                    System.out.println("-".repeat(15) + " Sort personnel by name in ascending order " + "-".repeat(15));
                    System.out.println("Sorting" + ".".repeat(20));

                    Collections.sort(personnelList);

                    System.out.println("=".repeat(140));
                    System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
                    System.out.println("=".repeat(140));

                    for (Personnel personnel : personnelList) {
                        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                        System.out.println("-".repeat(140));
                    }
                }
                case "2" -> {
                    System.out.println("-".repeat(15) + " Sort personnel by name in descending order " + "-".repeat(15));
                    System.out.println("Sorting" + ".".repeat(20));

                    Collections.sort(personnelList, Personnel.NameDescending);

                    System.out.println("=".repeat(140));
                    System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
                    System.out.println("=".repeat(140));

                    for (Personnel personnel : personnelList) {
                        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                        System.out.println("-".repeat(140));
                    }
                }
                case "3" -> {
                    System.out.println("-".repeat(15) + " Sort personnel by income in ascending order " + "-".repeat(15));
                    System.out.println("Sorting" + ".".repeat(20));

                    Collections.sort(personnelList, Personnel.IncomeAscending);

                    System.out.println("=".repeat(140));
                    System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
                    System.out.println("=".repeat(140));

                    for (Personnel personnel : personnelList) {
                        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                        System.out.println("-".repeat(140));
                    }
                }
                case "4" -> {
                    System.out.println("-".repeat(15) + " Sort personnel by name in descending order " + "-".repeat(15));
                    System.out.println("Sorting" + ".".repeat(20));

                    Collections.sort(personnelList, Personnel.IncomeDescending);

                    System.out.println("=".repeat(140));
                    System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
                    System.out.println("=".repeat(140));

                    for (Personnel personnel : personnelList) {
                        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
                        System.out.println("-".repeat(140));
                    }
                }
                case "5" -> {
                    isBack = true;
                }
            }
        }
    }

    private static void outputTop5PersonnelWithHighestIncome() {
        System.out.println();
        System.out.println("-".repeat(15) + " Output top 5 personnel with highest income " + "-".repeat(15));
        System.out.println("Outputting" + ".".repeat(20));
        Map<Integer, Personnel> personnelMap = company.getPersonnelMap();
        List<Personnel> personnelList = new ArrayList<>(personnelMap.size());
        for (Integer key : personnelMap.keySet()) {
            Personnel personnel = personnelMap.get(key);
            personnelList.add(personnel);
        }
        Collections.sort(personnelList, Personnel.IncomeDescending);

        System.out.println("=".repeat(140));
        System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20s | %-20s\n", "ID", "NAME", "PHONE", "EMAIL", "BASE SALARY", "INCOME");
        System.out.println("=".repeat(140));

        int count = 0;

        for (Personnel personnel : personnelList) {
            System.out.printf("| %-10s | %-25s | %-20s | %-30s | %-20.2f | %-20.2f\n", personnel.getId(), personnel.getName(), personnel.getPhone(), personnel.getEmail(), personnel.getBaseSalary(), personnel.getIncome());
            System.out.println("-".repeat(140));
            if (++count == 5) {
                break;
            }
        }
    }
}
