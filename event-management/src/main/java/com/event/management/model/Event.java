package com.event.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(name="marriage_type")
    private String marriageType;

    @Column(name="birthday_theme")
    private String birthdayTheme;

    @Column(name="college_type")
    private String collegeType;

    @Column(name="corporate_type")
    private String corporateType;

    private String date;
    private String location;
    private Double price;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMarriageType() { return marriageType; }
    public void setMarriageType(String marriageType) { this.marriageType = marriageType; }

    public String getBirthdayTheme() { return birthdayTheme; }
    public void setBirthdayTheme(String birthdayTheme) { this.birthdayTheme = birthdayTheme; }

    public String getCollegeType() { return collegeType; }
    public void setCollegeType(String collegeType) { this.collegeType = collegeType; }

    public String getCorporateType() { return corporateType; }
    public void setCorporateType(String corporateType) { this.corporateType = corporateType; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
