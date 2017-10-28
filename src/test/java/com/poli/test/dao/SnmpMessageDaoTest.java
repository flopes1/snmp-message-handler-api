// package com.poli.test.dao;
//
// import static org.junit.Assert.*;
//
// import java.util.List;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.poli.snmp.config.DatabaseConfiguration;
// import com.poli.snmp.dao.IObjectIdDao;
// import com.poli.snmp.model.Employee;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes =
// { DatabaseConfiguration.class })
// @Transactional
// public class SnmpMessageDaoTest
// {
//
// @Autowired
// private IObjectIdDao empDAO;
//
// @Test
// public void getEmployes()
// {
// try
// {
// List<Employee> employes = empDAO.getAllEmployees();
// assertEquals(employes != null, true);
// }
// catch (Exception e)
// {
// fail();
// }
// }
//
// }
