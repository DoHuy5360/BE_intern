package com.example.demo.kit.validation;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.entity.employee.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PrimitiveValidation {
    public ArrayList<String> errors = new ArrayList<String>();
    public Object entityFound;
    public String entityId;

    public boolean isValid() {
        return (this.errors.isEmpty()) ? true : false;
    }

    public ArrayList<String> getErrors() {
        return this.errors;
    }

    public int getAmountErrors() {
        return this.errors.size();
    }

    public boolean isBlank(String arg) {
        String regex = "^\\s*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arg);
        boolean isBlank = matcher.matches();
        return (isBlank) ? true : false;
    }

    public boolean isEqualLength(String arg, int len) {
        String regex = String.format("\\d{%d}", len);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arg);
        boolean isBlank = matcher.matches();
        return (isBlank) ? true : false;
    }

    public boolean isMatchMinMaxLength(String arg, int min, int max) {
        String regex = String.format("^.{%d,%d}$", min, max);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arg);
        boolean isMatch = matcher.matches();
        return (isMatch) ? true : false;
    }

    public boolean isIdValid(String name) {
        String regex = String.format("^%s[A-Za-z0-9]*(\\-[A-Za-z0-9]+)*$", name);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.entityId);
        boolean isMatch = matcher.matches();
        return (isMatch) ? true : false;
    }

    public boolean isDateValid(String string) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d+Z$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        boolean isMatch = matcher.matches();
        return (isMatch) ? true : false;
    }

}
