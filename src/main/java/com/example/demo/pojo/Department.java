package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//部门表
@Data
@AllArgsConstructor
@NoArgsConstructor

//借用Lombok,调用有参和无参构造器
public class Department {
    private Integer id;
    private String departmentName;
}
