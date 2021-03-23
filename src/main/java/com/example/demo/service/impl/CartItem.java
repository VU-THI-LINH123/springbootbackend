package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Dto.CartDto;
import com.example.demo.Dto.HoadonDto;
import com.example.demo.Dto.KhachhangDto;
import com.example.demo.Dto.SanphamDto;
import com.example.demo.entity.Chitietdonhang;
import com.example.demo.entity.Hoadon;
import com.example.demo.entity.Khachhang;
import com.example.demo.entity.Sanpham;
import com.example.demo.repository.HoadonRepository;
import com.example.demo.repository.KhachhangRepository;
import com.example.demo.repository.SanphamRepository;

@Service
@Transactional
public class CartItem {
	 @Autowired
	    private SanphamRepository sanphamRepository;
	 @Autowired
	 HttpSession session;
	    public HashMap<Long, CartDto> viewAdd(Long productId,int soluong) {
	    	 HashMap<Long, CartDto> cartItems = (HashMap<Long, CartDto>) session.getAttribute("CartItems");
		        if (cartItems == null) {
		            cartItems = new HashMap<>();
		        }
		        
		         Sanpham sanpham=null;
		         sanpham = sanphamRepository.getOne(productId);
		         int max=sanpham.getSoluong();
		        if (sanpham != null && max>=1) {//con hang
		            if (cartItems.containsKey(productId)) {
		            	 CartDto item = cartItems.get(productId);
		                if(max>=(item.getSoluong() + soluong)) {//hang nay khong du de cap nhat
		                item.setSanphamDto(new SanphamDto(sanpham));
		               
		                item.setSoluong(item.getSoluong() + soluong);
		                 item.setThanhtien(item.getSoluong()*sanpham.getGiaban());
		                cartItems.put(productId, item);}else {
		                	System.out.print("da het hang1");
		                	
		                }
		            } else {
		                CartDto item = new CartDto();
		                item.setSanphamDto(new SanphamDto(sanpham));
		                item.setSoluong(1);
		                item.setThanhtien(item.getSoluong()*sanpham.getGiaban());
		                cartItems.put(productId, item);
		                System.out.print("da het hang");
		            }
		        	
		        }  
		        session.setAttribute("CartItems", cartItems);
		      
		        return cartItems;
      }
	    
	    public HashMap<Long, CartDto> viewRemove(long productId) {
	        HashMap<Long, CartDto> cartItems = (HashMap<Long, CartDto>) session.getAttribute("CartItems");
	        if (cartItems == null) {
	            cartItems = new HashMap<>();
	        }
	        if (cartItems.containsKey(productId)) {
	            cartItems.remove(productId);
	        }
	        session.setAttribute("CartItems", cartItems);
	       
	        return cartItems;
	    }
	    @Autowired 
		HoadonRepository hoadonRepository;
	    @Autowired KhachhangRepository khachhangRepository;
	    @Autowired
		EntityManager manager;
		public HoadonDto saveOrUpdate(KhachhangDto dto) {
			HashMap<Long, CartDto> cartItems = (HashMap<Long, CartDto>) session.getAttribute("CartItems");
		    if(dto!=null)
		    {
		    	boolean check=false;
		    	Hoadon entity=new Hoadon();
		    	entity.setTrangthai("chuathanhtoan");
		    	String hql = "FROM Khachhang E WHERE E.sdt = :khachhang_sdt";
		    	Query q = manager.createQuery(hql);
		    	q.setParameter("khachhang_sdt",dto.getSodt());
		    	List<Khachhang> entities = q.getResultList();
		    	Khachhang t;
			    if( entities.size()>0)
			    {
			    	t=khachhangRepository.getOne(entities.get(0).getId());//khach hang da ton tai
			    }else {
			    	t=new Khachhang();
			    }
		    	//kiem tra xem khach hang da ton tai trong data chua so dien thoai la key
		    	t.setName(dto.getName());
		    	t.setDiachi(dto.getDiachi());
		    	t.setEmail(dto.getEmail());
		    	t.setSdt(dto.getSodt());
		        Set<Hoadon>hoadons=new HashSet<Hoadon>();
		        hoadons.add(entity);
		        
		        t.setHoadons(hoadons);
		    	entity.setKhachhang(t);
		    	
		        Set<Chitietdonhang>chitietdonhangs;
		        if(cartItems!=null)
		        {
		        	chitietdonhangs=new HashSet<Chitietdonhang>();
		        	for(CartDto cartdto:cartItems.values())
		        	{
		        		Chitietdonhang ct=new Chitietdonhang();
		        		ct.setHoadon(entity);
		        		ct.setSoluong(cartdto.getSoluong());
		        		Sanpham sp=sanphamRepository.getOne(cartdto.getSanpham().getId());
		        			sp.setSoluong(sp.getSoluong()-cartdto.getSoluong());
		        	
		        		ct.setSanpham(sp);
		        		chitietdonhangs.add(ct);
		        	}
		        	  entity.setChitietdonhangs(chitietdonhangs);
		        }
		 if(check==false) {
		  khachhangRepository.save(t);
		   entity= hoadonRepository.save(entity);
		   session.removeAttribute("myCartItems");
		   return new HoadonDto(entity);
		    }
		 }
		      return null;  
		}
		public HoadonDto huyhoadon(Long id) {
		    if(id!=null)
		    {
		    	Hoadon hoadon=hoadonRepository.getOne(id);
		    	if(hoadon.getTrangthai().equals("chuathanhtoan")) {
		    	Set<Chitietdonhang>danhsach=hoadon.getChitietdonhangs();
		    	if(danhsach!=null)
		    	{
		    		for(Chitietdonhang t:danhsach)
		    		{
		    			Sanpham sp=sanphamRepository.getOne(t.getSanpham().getId());
		    			sp.setSoluong(sp.getSoluong()+t.getSoluong());
		    		}
		    	}
		    	hoadonRepository.delete(hoadon);
		    	
		    	return new HoadonDto(hoadon);
		    }
		    }
		      return null;  
		}
		public HoadonDto thanhtoanhoadon(Long id) {
		    if(id!=null)
		    {
		    	Hoadon hoadon=hoadonRepository.getOne(id);
		    	    hoadon.setTrangthai("dathanhtoan");
		    	return new HoadonDto(hoadon);
		    }
		    
		      return null;  
		}
		
}

