package com.example.demo.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CartDto;
import com.example.demo.Dto.HoadonDto;
import com.example.demo.Dto.KhachhangDto;
import com.example.demo.Dto.SanphamDto;
import com.example.demo.Dto.SanphamSearchDt;
import com.example.demo.repository.SanphamRepository;
import com.example.demo.service.SanphamService;
import com.example.demo.service.impl.CartItem;

@CrossOrigin(origins = "http://localhost:3000")
@RestController 
@RequestMapping(path = "/api/sanpham")
public class SanphamController {
	@Autowired 
	SanphamService sanphamService;
	@RequestMapping(value = "/searchByPage", method=RequestMethod.POST)
	 public ResponseEntity<Page<SanphamDto>> searchByPage(@RequestBody SanphamSearchDt searchDto) {
			Page<SanphamDto> page = sanphamService.searchByPage(searchDto);

			return new ResponseEntity<>(page, HttpStatus.OK);
      }
	 @Autowired
	    private CartItem cartItem;
	    @RequestMapping(value = "add/{productId}/{soluong}", method = RequestMethod.GET)
	    public ResponseEntity<Page<CartDto>> liviewAdd( @PathVariable("productId") Long productId,@PathVariable("soluong")int soluong) {
	    	System.out.print("haha\n");
	    	HashMap<Long, CartDto>danhsach=cartItem.viewAdd(productId,soluong);
	    	Collection<CartDto> values = danhsach.values();
	    	List<CartDto> listOfValues = new ArrayList<CartDto>(values);
	    	Page<CartDto> result = new PageImpl<CartDto>(listOfValues);
	    	for(CartDto t:listOfValues)
	    	{
	    		System.out.print(t.getSoluong());
	    	}
	       return new ResponseEntity<>(result, HttpStatus.OK);
        }
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<SanphamDto> save(@RequestBody SanphamDto dto) {
		SanphamDto result =sanphamService.saveOrUpdate(dto, null);
		return new ResponseEntity<SanphamDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SanphamDto> update(@RequestBody SanphamDto dto,@PathVariable Long id) {
		SanphamDto result =sanphamService.saveOrUpdate(dto, id);
		return new ResponseEntity<SanphamDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	 @Autowired
	    private SanphamRepository sanphamRepository;
	
	   @RequestMapping(value = "remove/{productId}", method = RequestMethod.GET)
	    public ResponseEntity<Page<CartDto>> viewRemove1( @PathVariable("productId") long productId){
	    	System.out.print("haha\n");
	    	HashMap<Long, CartDto>danhsach=cartItem.viewRemove(productId);
	    	Collection<CartDto> values = danhsach.values();
	    	List<CartDto> listOfValues = new ArrayList<CartDto>(values);
	    	Page<CartDto> result = new PageImpl<CartDto>(listOfValues);
	
	       return new ResponseEntity<>(result, HttpStatus.OK);
	    }
	    public double totalPrice(HashMap<Long, CartDto> cartItems) {
	        int count = 0;
	       
	        return count;
	    }
	   
		@RequestMapping(value = "/submit", method = RequestMethod.POST)
		public ResponseEntity<HoadonDto> update(@RequestBody  KhachhangDto dto) {
			HoadonDto result =cartItem.saveOrUpdate(dto);
			return new ResponseEntity<HoadonDto>(result, (result != null) ?HttpStatus.OK : HttpStatus.BAD_REQUEST);
		}
		@RequestMapping(value = "/huyhoadon/{id}", method = RequestMethod.GET)
		public ResponseEntity<HoadonDto> huyhoadon(@PathVariable("id") long id) {
			HoadonDto result =cartItem.huyhoadon(id);
			return new ResponseEntity<HoadonDto>(result, (result != null) ?HttpStatus.OK : HttpStatus.BAD_REQUEST);
		}
}
