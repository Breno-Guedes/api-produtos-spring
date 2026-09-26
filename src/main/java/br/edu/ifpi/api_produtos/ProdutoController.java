package br.edu.ifpi.api_produtos;

import br.edu.ifpi.api_produtos.Produto;
import br.edu.ifpi.api_produtos.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    // List<Produto> produtos = new ArrayList<>();

    // GET /produtos - Listar todos
    @GetMapping
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    // GET /produtos/{id} - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /produtos - Adicionar novo produto
    @PostMapping
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        if (produto != null && produto.getNome() != null && produto.getCategoria() != null && produto.getPreco() != null) {
            repository.save(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado");
        } else {
            return ResponseEntity.badRequest().body("Produto inválido");
        }
    }

    // PUT /produtos/{id} - Atualizar produto existente
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto != null && produto.getNome() != null && produto.getCategoria() != null && produto.getPreco() != null) {
            return repository.findById(id)
                    .map(p -> {
                        p.setNome(produto.getNome());
                        p.setCategoria(produto.getCategoria());
                        p.setPreco(produto.getPreco());
                        repository.save(p);
                        return ResponseEntity.ok("Produto atualizado");
                    })
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"));
        } else {
            return ResponseEntity.badRequest().body("Produto inválido");
        }
    }

    // DELETE /produtos/{id} - Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Produto deletado");
    }
}