package springjbdc.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springjbdc.mapper.Products;
import springjbdc.repository.RepositoryJDBC;

import java.util.List;

@RestController
public class ControlJDBC {

    private final RepositoryJDBC repositoryJDBC;

    public ControlJDBC(RepositoryJDBC repositoryJDBC) {
        this.repositoryJDBC = repositoryJDBC;
    }

    @GetMapping("/products/fetch-product")
    public List<Products> getSqlSetup(@RequestParam String name) {
        return repositoryJDBC.getProductName(name);
    }
    @PostMapping("/post")
    public List<Products> getSqlSetups(@RequestParam String name) {
        return repositoryJDBC.getProductName(name);
    }
}
