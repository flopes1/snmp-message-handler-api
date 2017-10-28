// package com.poli.test.service;
//
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.fail;
//
// import java.util.List;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.poli.snmp.config.DatabaseConfiguration;
// import com.poli.snmp.model.Employee;
// import com.poli.snmp.service.IObjectIdService;
//
// @WebAppConfiguration
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes =
// { DatabaseConfiguration.class })
// @Transactional
// public class SnmpMessageServiceTest
// {
//
// @Autowired
// private IObjectIdService empService;
//
// @Test
// public void getEmployes()
// {
// try
// {
// List<Employee> employes = empService.getAllEmployees();
// assertNotNull(employes);
// }
// catch (Exception e)
// {
// fail();
// }
// }
//
// }
