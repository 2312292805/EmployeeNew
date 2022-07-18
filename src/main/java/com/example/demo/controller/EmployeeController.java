package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.Collection;
//用户信息控制器，用于存储用户的信息
@Controller
public class EmployeeController {
    @Autowired
    //遍历员工信息操作
    EmployeeDao employeeDao;
    @Autowired
    //遍历部门信息
    DepartmentDao departmentDao;
    @RequestMapping("/emps")
    public String list(Model model){
        //使用集合用于存储所有的员工信息
        Collection<Employee> employees= employeeDao.getAll();
        //
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    //对于所有部门进行读取
    @GetMapping("/emp")
    public String toAddpage(Model model){
        //读取所有人员的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    //对于提交的添加人员信息进行保存
    @PostMapping("/emp")
    public String addemp(Employee employee){
        System.out.println("save===>"+employee);
        employeeDao.save(employee);//保存员工的信息
        //添加的操作以后，跳转到商城员工页
        return "redirect:/emps";
    }

    //跳转到员工信息更新页面
    @GetMapping("/emp/{id}")
    //model用于将获取到的员工数据提交给前端
    public String toUpdateEmp(@PathVariable("id")Integer id, Model model){
        //查询员工的数据
        Employee employee= employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //回显部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    //员工信息修改保存
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工的操作
    @RequestMapping("/delemp/{id}")
    //@PathVariable("id")int id获得该用户ID的路径
    public String deleteEmp(@PathVariable("id")int id){
        //删除用户ID
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
