package com.nghia3;

import java.util.*;

public class Company {

    private String name;
    private String location;
    private Map<Integer, Personnel> personnelMap;

    public Company(String name, String location) {
        this.name = name;
        this.location = location;
        personnelMap = new LinkedHashMap<>();
        addPersonnel(new Manager("La Trong Nghia", "0399308301", "leonghiacnn@gmail.com", 30_000_000.0, 8_000_000.0));
        addPersonnel(new SalesStaff("Nguyen Thi Huong", "0986504232", "huong2k4@gmail.com", 10_000_000.0, 30_000_000.0, 0.2));
        addPersonnel(new AdministrativeStaff("Trinh Dinh Quoc", "0989891020", "trinhquoc2k4@gmail.com", 12_000_000.0));
        addPersonnel(new AdministrativeStaff("Nguyen Huu Hung", "09843374820", "hungmoklob@gmail.com", 15_000_000.0));
        addPersonnel(new Manager("Truong Van Nhu", "0988594690", "truongnhu96@gmail.com", 30_000_000.0, 8_000_000.0));
        addPersonnel(new SalesStaff("Dinh Doanh Viet", "0984336748", "quocviet@gmail.com", 10_000_000.0, 30_000_000.0, 0.2));
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Map<Integer, Personnel> getPersonnelMap() {
        return Collections.unmodifiableMap(personnelMap);
    }

    public void addPersonnel(Personnel personnel) {
        personnelMap.put(personnel.getId(), personnel);
    }

    public Personnel findPersonnel(int id) {
        return personnelMap.get(id);
    }

    public void deletePersonnel(int id) {
        personnelMap.remove(id);
    }

    public List<Personnel> findPersonnelBySalaryRange(double lowerRange, double higherRange) {
        List<Personnel> personnelList = new LinkedList<>();
        for (Integer key : personnelMap.keySet()) {
            Personnel personnel = personnelMap.get(key);
            double baseSalary = personnel.getBaseSalary();
            if (baseSalary >= lowerRange && baseSalary <= higherRange) {
                personnelList.add(personnel);
            }
        }
        return personnelList;
    }
}
