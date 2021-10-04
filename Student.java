package com.technoelevate.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.hibernet.bean.Employee;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import lombok.Data;

@Data
@Entity
public class Student implements Serializable {
	@Id
	private int roll_no;
	private String name;
	private long phone_no;
	private String standard;
	static Scanner scanner=new Scanner(System.in);
	
	public void seeAllTheData() {
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			String findAll="from Student";
			Query query=manager.createQuery(findAll);
			List<Student> list=query.getResultList();
			for(Student student : list) {
				System.out.println(student);
			}
		}
		catch(Exception e1) {
			e1.printStackTrace();
			
		}
		finally {
			if(factory!=null)
				factory.close();
			if(manager!=null)
				manager.close();
			
		}

	}
	
	//see any particular data//
	public void seeParticularData() {
		
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			System.out.print("enter rollNo");
			int id=scanner.nextInt();
			String findById="from Student where roll_no=:i";
			Query query=manager.createQuery(findById);
			query.setParameter("i", id);
			Student student=(Student)query.getSingleResult();
			
				System.out.println(student);
			}
		catch(Exception e1) {
			throw new IdNotFoundException();
			
		}
		finally {
			if(factory!=null)
				factory.close();
			if(manager!=null)
				manager.close();
			
		}

		
	}

	public void updateData() {
		
		System.out.println("Enter the roll no you wish to update");
		int roll_no=scanner.nextInt();
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			String findById="from Student where roll_no=:i";
			Query query=manager.createQuery(findById);
			query.setParameter("i",roll_no);
			Student student1=(Student)query.getSingleResult();
			if(student1==null) {
				throw new IdNotFoundException();
			}
			else {
				System.out.println("Do you want to update the name (y/n):");
				char ch=scanner.next().charAt(0);
				if(ch=='y' || ch=='Y') {
					System.out.print("Enter new name");
					String new_name=scanner.next();
					EntityTransaction transaction=null;
					try {
						transaction=manager.getTransaction();
						transaction.begin();
						String update="update Student set name= :name where roll_no=:no";
						query=manager.createQuery(update);
						query.setParameter("name",new_name );
						query.setParameter("no",roll_no );
						
						int a=query.executeUpdate();
						transaction.commit();
						
						}catch(Exception e1) {
							e1.printStackTrace();
							if(transaction!=null)
								transaction.rollback();
						}
					}

				}
			System.out.print("You want update phone number y/n");
			char user_input=scanner.next().charAt(0);
			if(user_input=='Y' || user_input=='y') {
				System.out.print("Enter new phone number");
				long new_phno=scanner.nextLong();
				EntityTransaction transaction=null;
				try {
					transaction=manager.getTransaction();
					transaction.begin();
					String update="update Student set phone_no= :phno where roll_no=:no";
					query=manager.createQuery(update);
					query.setParameter("phno",new_phno );
					query.setParameter("no",roll_no );
					
					int a=query.executeUpdate();
					transaction.commit();
					
					}catch(Exception e1) {
						e1.printStackTrace();
						if(transaction!=null)
							transaction.rollback();
					}
				}
			System.out.print("You want update Standard y/n");
			char input=scanner.next().charAt(0);
			if(input=='Y' || input=='y') {
				System.out.print("Enter new standard");
				String stand=scanner.next();
				EntityTransaction transaction=null;
				try {
					transaction=manager.getTransaction();
					transaction.begin();
					String update="update Student set standard= :s where roll_no=:no";
					query=manager.createQuery(update);
					query.setParameter("s",stand );
					query.setParameter("no",roll_no );
					
					int a=query.executeUpdate();
					transaction.commit();
					
					}catch(Exception e1) {
						e1.printStackTrace();
						if(transaction!=null)
							transaction.rollback();
					}
	
			
			}
		}
	
	catch(NoResultException e) {
		throw new IdNotFoundException();
		
			
	}
		finally {
			if(factory!=null)
				factory.close();
			if(manager!=null)
				manager.close();
		}
		
}

	public void deleteData() {
		System.out.println("Enter the roll no you wish to delete");
		int roll_no=scanner.nextInt();
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			transaction=manager.getTransaction();
			transaction.begin();
			String delete="delete Student where roll_no=:i";
			Query query=manager.createQuery(delete);
			query.setParameter("i",roll_no);
			int a=query.executeUpdate();
			transaction.commit();
			}

		catch(NoResultException e) {
			throw new IdNotFoundException();
			
				
		}
			finally {
				if(factory!=null)
					factory.close();
				if(manager!=null)
					manager.close();
			}
		
	
	
}
}