package io.projetocoletarsu.repository;

import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.enums.StatusColeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    Optional<List<Agendamento>> findAgendamentosByUsuarioIdOrderByDataAgendada(Integer usuario);

    /* select * fromo agendamentos where id_usuario = x and status like 'CANCELADO' */
    Optional<List<Agendamento>> findAgendamentosByUsuarioIdAndStatusOrderByDataAgendada(Integer usuario, StatusColeta statusColeta);

    Optional<List<Agendamento>> findAgendamentosByStatusOrderByDataAgendada(StatusColeta statusColeta);


    Optional<List<Agendamento>> findAgendamentosByStatusAndDataAgendadaBetweenOrderByDataAgendada(StatusColeta statusColeta, Date dataInicio, Date dataFim);

    Optional<List<Agendamento>> findAgendamentosByDataAgendadaBetweenOrderByDataAgendada(Date dataInicio, Date dataFim);
}
