package model.testes;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import db.DB;
import model.entities.Department;
import model.entities.Seller;
import model.services.DepartmentService;
import model.services.SellerService;

public class teste2 {
	
	@Test
	public void AbrirUmaConexão() {
		boolean a;
		DB conexao= new DB();
		if(conexao.getConnection()!=null) {
			a = true;
		}else {
			a=false;
		}
		assertEquals(true, a);
	}
	
	@Test
	public void criarDepartamento() {
			int a;
			List<Department> departamentos = new DepartmentService().findAll();
			a = departamentos.size();
			Department musica = new Department();
			new DepartmentService().saveOrUpdate(musica);
			departamentos = new DepartmentService().findAll();
			assertEquals(a+1, departamentos.size());
			
	}
	@Test
	public void deletarDepartment() {
		int a;
		Department musica = new Department();
		new DepartmentService().saveOrUpdate(musica);
		List<Department> departamentos = new DepartmentService().findAll();
		a = departamentos.size();
		new DepartmentService().remove(musica);
		departamentos = new DepartmentService().findAll();
		assertEquals(a-1, departamentos.size());
	}
	
	@Test
	public void criarSeller() {
		int a;
		List<Seller> vendedores = new SellerService().findAll();
		a = vendedores.size();
		Department musica = new Department(20,"musica");
		new DepartmentService().saveOrUpdate(musica);
		Date c= new Date();
		Seller Amanda = new Seller(10,"a","a",c,1000.0,musica);
		new SellerService().saveOrUpdate(Amanda);
		vendedores = new SellerService().findAll();
		assertEquals(a, vendedores.size());
	}
	
	@Test
	public void deletarSeller() {
		int a;
		Date c= new Date();
		Department musica = new Department(20,"musica");
		new DepartmentService().saveOrUpdate(musica);
		Seller Amanda = new Seller(10,"a","a",c,1000.0,musica);
		new SellerService().saveOrUpdate(Amanda);
		List<Seller> vendedores = new SellerService().findAll();
		a = vendedores.size();
		new SellerService().remove(Amanda);
		vendedores = new SellerService().findAll();
		assertEquals(a, vendedores.size());
	}
	@Test
	public void alterarDepartment() {
		Department musica = new Department(20,"musica");
		new DepartmentService().saveOrUpdate(musica);
		List<Department> departamentos = new DepartmentService().findAll();
		for(Department department : departamentos) {
			if(department.getId() == 20) {
				musica = department;
			}
		}
		assertEquals("musica",musica.getName());
		musica.setName("sala de musica");
		new DepartmentService().saveOrUpdate(musica);
		for(Department department : departamentos) {
			if(department.getId() == 20) {
				musica = department;
			}
		}
		assertEquals("sala de musica", musica.getName());
	}
	
}