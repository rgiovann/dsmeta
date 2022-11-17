package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;

@Service
public class SaleService {

		@Autowired
		private SaleRepository repository;
		
		public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable){
 			
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
			LocalDate max = maxDate.equals("")? today: LocalDate.parse(maxDate);
			LocalDate min = minDate.equals("")? today.minusDays(365): LocalDate.parse(minDate);

			
			return repository.findSales(min, max, pageable);						
			
	}
		
		// create method to return an user by Id
		//	
		public Sale findById(Long id) {
			Optional<Sale> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ResourceNotFoundException(id));
		}

		public Sale insert(Sale obj) {
			obj.setId(null);
			return repository.save(obj);
		}

		
}
