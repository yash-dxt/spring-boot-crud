package com.example.collegemanager;


import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    private String id;

    @NonNull
    private  String name;

    @Indexed(unique = true)
    @NonNull
    private final int rollNumber;

    @NonNull
    private  Section section;
    @NonNull
    private  Branch branch;
    @NonNull
    private  Integer year;
    @NonNull
    private Boolean feePaid;

    public Student(@NonNull String name, @NonNull int rollNumber, @NonNull Section section, @NonNull Branch branch, @NonNull Integer year) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.section = section;
        this.branch = branch;
        this.year = year;
        this.feePaid = false;
    }

    public void setFeePaid(Boolean feePaid){
        this.feePaid = feePaid;
    }
    public Boolean getFeePaid() {
        return feePaid;
    }

    public Integer getYear() {
        return year;
    }

    public Branch getBranch() {
        return branch;
    }

    public Section getSection() {
        return section;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }
}
