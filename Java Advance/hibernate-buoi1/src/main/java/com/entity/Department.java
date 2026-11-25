package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {

    @Id// khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto_increment
    @Column(name = "department_id")// mapping với column có tên là department_id ở trong bảng department
    private Integer id;

    @Column(name = "department_name", length = 100, nullable = false, unique = true)
    private String name;//department_name       varchar(100)       not null      unique
}
