package set_;

import java.util.HashSet;
import java.util.Objects;

public class HashSetExercise {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("milan",100,new MyDate(1998,1,1)));
        hashSet.add(new Employee("smith",200,new MyDate(1998,1,1)));
        hashSet.add(new Employee("milan",100,new MyDate(1998,1,1)));

        System.out.println("hashset=" + hashSet);
    }
}

//创建Employee对象
class Employee {
    private String name;
    private int salary;
    private MyDate birthday;

    public Employee(String name, int salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }

    //如果name和birthday相同，则返回相同的hash值
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(birthday, employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myData = (MyDate) o;
        return year == myData.year && month == myData.month && day == myData.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}