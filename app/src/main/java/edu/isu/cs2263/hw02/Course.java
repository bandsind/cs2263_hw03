package edu.isu.cs2263.hw02;

public class Course {


    public static final String[] departmentNames = {"Computer Science", "Mathematics", "Chemistry", "Physics", "Biology", "Electrical Engineering"};

    public static final String[] departments = {"CS", "MATH", "CHEM", "PHYS", "BIOL", "EE"};


    private String name;
    private int number;
    private int credit;
    private String departmentCode;

    public Course(String name, int number, int credit, String departmentCode) {
        this.name = name;
        this.number = number;
        this.credit = credit;
        this.departmentCode = departmentCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    @Override
    public String toString() {
        return " " + departmentCode + "  " +name + "  " + number;
    }

    /**
     * Utility method to map a department name to a department code
     *
     * @param input The department name
     * @return the department code, if able to be found. Null otherwise
     */
    public static String getDeptFromName(String input) {
        for (int i = 0; i < departmentNames.length; i++) {
            if (departmentNames[i].equals(input)) {
                return departments[i];
            }
        }

        return null;
    }



}
