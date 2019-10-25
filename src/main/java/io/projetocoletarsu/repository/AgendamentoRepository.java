package io.projetocoletarsu.repository;

import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.enums.StatusColeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    Optional<List<Agendamento>> findAgendamentosByUsuarioIdOrderByDataAgendada(Integer usuario);

    List<Agendamento> findAgendamentosByStatus(StatusColeta statusColeta);
}
