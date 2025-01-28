package me.jmarango.base.service;

import org.springframework.data.jpa.domain.AbstractPersistable;

public interface ModelService<MODEL extends AbstractPersistable<?>, DTO> {
    MODEL mapToModel(DTO dto);
    DTO mapToDTO(MODEL model);
}