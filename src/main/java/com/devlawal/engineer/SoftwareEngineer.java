package com.devlawal.engineer;

import java.util.List;
import java.util.Objects;

public class SoftwareEngineer {
    int id;
    String name;
    List<String> stack;


    public SoftwareEngineer() {
    }

    public SoftwareEngineer(int id, String name, List<String> stack) {
        this.id = id;
        this.name = name;
        this.stack = stack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareEngineer that = (SoftwareEngineer) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(stack, that.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stack);
    }
}
