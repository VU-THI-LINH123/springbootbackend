package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.Dto.SanphamDto;
import com.example.demo.Dto.SanphamSearchDt;

public interface SanphamService {
	public Page<SanphamDto> searchByPage(SanphamSearchDt dto);
	public SanphamDto saveOrUpdate(SanphamDto dto, Long id);
}
