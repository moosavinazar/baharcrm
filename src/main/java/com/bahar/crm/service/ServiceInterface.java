package com.bahar.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ServiceInterface<DTO> {

    public DTO save(DTO dto);

    public DTO update(DTO dto);

    @Transactional(readOnly = true)
    public Page<DTO> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    public Optional<DTO> findOne(Long id);

    public void delete(Long id);

}
