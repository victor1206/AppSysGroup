package org.esfe.appsysgroup.repositorios;

import org.esfe.appsysgroup.modelos.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoRepository extends JpaRepository<Grupo,Integer> {
}
