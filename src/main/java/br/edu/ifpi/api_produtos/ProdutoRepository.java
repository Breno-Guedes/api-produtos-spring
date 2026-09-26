package br.edu.ifpi.api_produtos;

import br.edu.ifpi.api_produtos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
