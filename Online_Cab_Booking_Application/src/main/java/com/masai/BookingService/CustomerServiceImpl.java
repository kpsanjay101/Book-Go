package com.masai.BookingService;

import java.util.List;

import com.masai.BookingDAO.EMUtils;
import com.masai.BookingEntity.Customer;
import com.masai.Exception.NoRecordFoundException;
import com.masai.Exception.SomethingWentWrongException;
import com.masai.Exception.UserNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public void insertCustomer(Customer cust) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(cust);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
	}

//	@Override
//	public void updateCustomer(int customerId,String address, String password, String email, String mobileNo) {
//		EntityManager em = EMUtils.getEntityManager();
//		EntityTransaction et = em.getTransaction();
//		
//		String updateQuery = "SELECT c FROM Customer c WHERE c.customerId = :custId";
//		Query query = em.createQuery(updateQuery);
//		query.setParameter("custId", customerId);
//
//		Customer cust = (Customer)query.getSingleResult();
//		
//		try {
//			if(cust == null) {
//				throw new UserNotFoundException("Customer doesn't Exist whith this id");
//			}
//			et.begin();
//			if(password != "") {
//				cust.setPassword(password);
//			}else {
//				cust.setPassword(cust.getPassword());
//			}
//			
//            if(address != "") {
//            	cust.setAddress(address);
//			}else {
//				cust.setAddress(cust.getAddress());
//			}
//			
//			if(email != "") {
//				cust.setEmail(email);		
//			}else {
//				cust.setEmail(cust.getEmail());
//			}
//			
//			if(mobileNo != "") {
//				cust.setMobileNo(mobileNo);			
//			}else {
//				cust.setMobileNo(cust.getMobileNo());
//			}
//			et.commit();
//		}catch(PersistenceException e) {
//			throw new SomethingWentWrongException("Something went wrong");
//		}finally {
//			em.close();
//		}
//		
//	}

	@Override
	public void deletetCustomer(int customerId) {
		EntityManager em = EMUtils.getEntityManager();
		EntityTransaction et = em.getTransaction();
		String deleteQuery = "SELECT c FROM Customer c WHERE c.customerId = :custId";
		Query query = em.createQuery(deleteQuery);
		query.setParameter("custId", customerId);
		Customer cust = (Customer)query.getSingleResult();
		try {
			if(cust == null) {
				throw new UserNotFoundException("Customer doesn't Exist whith this id");
			}
			et.begin();
			em.remove(cust);
			et.commit();
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
	}

	@Override
	public List<Customer> viewCustomer() {
		EntityManager em = EMUtils.getEntityManager();
		String viewQuery = "SELECT c FROM Customer c ";
		Query query = em.createQuery(viewQuery);
		List<Customer> list = null;
		try {
			list = query.getResultList();
			if(list.isEmpty()) {
				throw new NoRecordFoundException("No Record Found");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
		return list;
	}

	@Override
	public Customer viewCustomerById(int customerId) {
		EntityManager em = EMUtils.getEntityManager();
		String viewQuery = "SELECT c FROM Customer c WHERE c.customerId = :id";
		Query query = em.createQuery(viewQuery);
		query.setParameter("id", customerId);
		Customer cust = (Customer)query.getSingleResult();
		try {
			if(cust == null) {
				throw new NoRecordFoundException("NO data found with this id");
			}
		}catch(PersistenceException e) {
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			em.close();
		}
		
		return cust;
	}

	@Override
	public Customer validateCustomer(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
