package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.projection.SaleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFinal;
		LocalDate dataInicial;
		if(maxDate.isEmpty()) {
			dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else {
			dataFinal = LocalDate.parse(maxDate, formatoData);
		}

		if(minDate.isEmpty()) {
			dataInicial = dataFinal.minusYears(1L);
		}
		else {
			dataInicial = LocalDate.parse(minDate, formatoData);
		}

		Page<SaleProjection> result = repository.searchSale(dataInicial, dataFinal, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}
}
