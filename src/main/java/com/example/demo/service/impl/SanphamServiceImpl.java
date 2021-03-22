package com.example.demo.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.Dto.AnhDto;
import com.example.demo.Dto.SanphamDto;
import com.example.demo.Dto.SanphamSearchDt;
import com.example.demo.entity.Anh;
import com.example.demo.entity.Loaisp;
import com.example.demo.entity.Sanpham;
import com.example.demo.repository.AnhRepository;
import com.example.demo.repository.LoaiSPRepository;
import com.example.demo.repository.SanphamRepository;
import com.example.demo.service.SanphamService;


@Service
@Transactional
public class SanphamServiceImpl implements SanphamService {
	@Autowired
	EntityManager manager;
	public Page<SanphamDto> searchByPage(SanphamSearchDt dto) {
		if (dto == null) {
			return null;
		}

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();

		if (pageIndex > 0) {
			pageIndex--;
		} else {
			pageIndex = 0;
		}

		String whereClause = "";
		
		String orderBy = " ORDER BY entity.id DESC";
		if (dto.getOrderBy() != null && StringUtils.hasLength(dto.getOrderBy().toString())) {
			orderBy = " ORDER BY entity."+dto.getOrderBy()+" ASC";
		}
		
		String sqlCount = "select count(entity.id) from Sanpham as entity where (1=1)";
		String sql = "select new  com.example.demo.Dto.SanphamDto(entity) from Sanpham as entity where (1=1)";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND (UPPER(entity.loaisp.name) LIKE UPPER(:text))";
		}

      
		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, SanphamDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<SanphamDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult(); 
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<SanphamDto> result = new PageImpl<SanphamDto>(entities, pageable, count);
		return result;
	}
	@Autowired
	SanphamRepository sanphamRepository;
	@Autowired
	LoaiSPRepository loaiSPRepository;
	@Autowired
	AnhRepository anhRepository;
	public SanphamDto saveOrUpdate(SanphamDto dto, Long id) {
	    if(dto!=null)
	    {
	    	Sanpham entity=null;
	    	if(id!=null && dto.getId().equals(id))
	    	{
	            entity=sanphamRepository.getOne(id);
	            //xoa ta ca cac anh di
	            if(entity.getAnhs()!=null)
	            {
	            	for(Anh anh:entity.getAnhs())
	            	{
	            		anhRepository.delete(anh);
	            	}
	            }
	    	}
	    	if(entity==null)
	    	{
	    		entity=new Sanpham();
	    	}
	    	entity.setName(dto.getName());
	    	entity.setGiaban(dto.getGiaban());
	    	entity.setGianhap(dto.getGianhap());
	    	Loaisp loaisp=loaiSPRepository.getOne(dto.getLoaiDto().getId());
	    	entity.setTrangthai(dto.getTrangthai());
	    	entity.setSoluong(dto.getSoluong());
	    	entity.setLoaisp(loaisp);
	        Set<Anh>anhs;
	        if(dto.getAnhDtos()!=null)
	        {
	        	anhs=new HashSet<Anh>();
	        	for(AnhDto item:dto.getAnhDtos())
	        	{
	        		if(item!=null)
	        		{
	        			Anh anhsp=new Anh();
	        			anhsp.setUrl(item.getUrl());
	        			anhsp.setSanpham(entity);
	        			anhs.add(anhsp);
	        		}
	        	}
	        	 entity.setAnhs(anhs);
	        }
	   
	   entity= sanphamRepository.save(entity);
	   return new SanphamDto(entity);
	    }
	      return null;  
	}
	
}
