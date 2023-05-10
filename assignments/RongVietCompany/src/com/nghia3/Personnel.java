package com.nghia3;

import java.util.Comparator;

public abstract class Personnel implements Comparable<Personnel> {

    private static int count = 0;
    private final int id;
    private final String name;
    private String phone;
    private String email;
    private double baseSalary;

    public Personnel(String name, String phone, String email, double baseSalary) {
        id = ++count;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.baseSalary = baseSalary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract double getIncome();

    public double getTax() {
        if (getIncome() < 9_000_000) {
            return 0.0;
        } else if (getIncome() >= 9_000_000 && getIncome() <= 15_000_000) {
            return 0.1;
        } else {
            return 0.12;
        }
    }

    @Override
    public abstract String toString();

    public static Comparator<Personnel> NameDescending = new Comparator<Personnel>() {
        @Override
        public int compare(Personnel o1, Personnel o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };

    public static Comparator<Personnel> IncomeAscending = new Comparator<Personnel>() {
        @Override
        public int compare(Personnel o1, Personnel o2) {
            if (o1.getIncome() < o2.getIncome()) {
                return -1;
            } else if (o1.getIncome() > o2.getIncome()) {
                return 1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }
    };

    public static Comparator<Personnel> IncomeDescending = new Comparator<Personnel>() {
        @Override
        public int compare(Personnel o1, Personnel o2) {
            if (o1.getIncome() < o2.getIncome()) {
                return 1;
            } else if (o1.getIncome() > o2.getIncome()) {
                return -1;
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }
    };
}

class AdministrativeStaff extends Personnel {

    public AdministrativeStaff(String name, String phone, String email, double baseSalary) {
        super(name, phone, email, baseSalary);
    }

    @Override
    public double getIncome() {
        return getBaseSalary();
    }

    @Override
    public String toString() {
        return String.format("Administrative Staff [id = %d, name = %s, phone = %s, email = %s, income = %.2f]", getId(), getName(), getPhone(), getEmail(), getIncome());
    }

    @Override
    public int compareTo(Personnel o) {
        return this.getName().compareTo(o.getName());
    }
}

class SalesStaff extends Personnel {

    private double sales;
    private double commission;

    public SalesStaff(String name, String phone, String email, double baseSalary, double sales, double commission) {
        super(name, phone, email, baseSalary);
        this.sales = sales;
        this.commission = commission;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Override
    public double getIncome() {
        return getBaseSalary() + sales * commission;
    }

    @Override
    public String toString() {
        return String.format("Sales Staff [id = %d, name = %s, phone = %s, email = %s, income = %.2f]", getId(), getName(), getPhone(), getEmail(), getIncome());
    }

    @Override
    public int compareTo(Personnel o) {
        return this.getName().compareTo(o.getName());
    }
}

class Manager extends Personnel {

    private double responsibleSalary;

    public Manager(String name, String phone, String email, double baseSalary, double responsibleSalary) {
        super(name, phone, email, baseSalary);
        this.responsibleSalary = responsibleSalary;
    }

    public void setResponsibleSalary(double responsibleSalary) {
        this.responsibleSalary = responsibleSalary;
    }

    @Override
    public double getIncome() {
        return getBaseSalary() + responsibleSalary;
    }

    @Override
    public String toString() {
        return String.format("Manager [id = %d, name = %s, phone = %s, email = %s, income = %.2f]", getId(), getName(), getPhone(), getEmail(), getIncome());
    }

    @Override
    public int compareTo(Personnel o) {
        return this.getName().compareTo(o.getName());
    }
}
