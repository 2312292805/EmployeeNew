package com.example.demo.dao;

import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees=null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees=new HashMap<Integer,Employee>();
        employees.put(1001,new Employee(1001,"张三","1111@qq.com",0,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"李四","2222@qq.com",1,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"王五","3333@qq.com",0,new Department(103,"运营部")));
        employees.put(1004,new Employee(1004,"Jame","8888@qq.com",0,new Department(103,"运营部")));
        employees.put(1005,new Employee(1005,"Kobe","5555@qq.com",0,new Department(105,"后勤部")));
    }
    //主键自增
    private static Integer initId=1006;
    //增加一个员工
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initId++);
        }
        //部门编号自增
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }
    //查询所有员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }
    //通过ID查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    //删除员工
    public void delete(Integer id){
        employees.remove(id);
    }
}
