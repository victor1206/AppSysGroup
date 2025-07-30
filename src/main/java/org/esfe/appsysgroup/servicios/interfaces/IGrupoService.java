package org.esfe.appsysgroup.servicios.interfaces;

import org.esfe.appsysgroup.modelos.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    Page<Grupo> buscarTodosPaginados(Pageable pageable);

    List<Grupo> buscarTodos();

    Optional<Grupo> buscarPorId(Integer id);

    Grupo createOrEdit(Grupo grupo);

    void eliminarPorId(Integer id);
}
