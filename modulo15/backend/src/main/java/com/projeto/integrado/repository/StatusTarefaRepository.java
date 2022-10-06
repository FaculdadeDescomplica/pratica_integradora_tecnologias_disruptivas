package com.projeto.integrado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.integrado.entity.StatusTarefa;

public interface StatusTarefaRepository extends JpaRepository<StatusTarefa, Integer> {}