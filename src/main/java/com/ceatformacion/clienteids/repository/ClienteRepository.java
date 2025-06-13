package com.ceatformacion.clienteids.repository;

import com.ceatformacion.clienteids.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Clientes,Integer> {// se debe llamar igual que la clase del repositorio al que que esta haciendo la llamada y el tipo dato de la clave principal en tipo objeto
}
