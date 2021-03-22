package com.example.demo.service.impl;

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
import com.example.demo.Dto.CartDto;
import com.example.demo.Dto.HoadonDto;
import com.example.demo.Dto.HoadonDtoSearch;
import com.example.demo.Dto.SanphamDto;
import com.example.demo.Dto.SanphamSearchDt;
import com.example.demo.entity.Anh;
import com.example.demo.entity.Chitietdonhang;
import com.example.demo.entity.Hoadon;
import com.example.demo.entity.Khachhang;
import com.example.demo.entity.Loaisp;
import com.example.demo.entity.Sanpham;
import com.example.demo.repository.AnhRepository;
import com.example.demo.repository.HoadonRepository;
import com.example.demo.repository.LoaiSPRepository;
import com.example.demo.repository.SanphamRepository;
@Service
@Transactional
public class HoadonImpl {
 
	@Autowired
	EntityManager manager;
	public Page<HoadonDto> searchByPage(HoadonDtoSearch dto) {
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
		
		String sqlCount = "select count(entity.id) from Hoadon as entity where (1=1)";
		String sql = "select new  com.example.demo.Dto.SanphamDto(entity) from Hoadon as entity where (1=1)";

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND UPPER(entity.name) LIKE UPPER(:text) )";
		}


		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, HoadonDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<HoadonDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult(); 
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<HoadonDto> result = new PageImpl<HoadonDto>(entities, pageable, count);
		return result;
	}
	@Autowired
	SanphamRepository sanphamRepository;
	
}
