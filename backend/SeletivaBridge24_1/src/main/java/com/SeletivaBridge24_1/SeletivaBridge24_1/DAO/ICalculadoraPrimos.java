package com.SeletivaBridge24_1.SeletivaBridge24_1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SeletivaBridge24_1.SeletivaBridge24_1.model.CalculadoraPrimos;


// Classe que permite operações CRUD no banco de dados para objetos do tipo CalculadoraPrimos.
public interface ICalculadoraPrimos extends JpaRepository<CalculadoraPrimos, Integer>{

}
