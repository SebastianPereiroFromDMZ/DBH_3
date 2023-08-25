package springjbdc.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import springjbdc.mapper.Products;
import springjbdc.mapper.RowMapperProducts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositoryJDBC {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public RepositoryJDBC(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Products> getProductName(String name) {

        String sql = read("myScript.sql");
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return new ArrayList<>(namedParameterJdbcTemplate.query(sql, parameterSource, new RowMapperProducts()));

        //String query = "select product_name from netology.orders join netology.customers on customers.id = customer_id where name = 'Николай'";
        //List<Products> productsList = namedParameterJdbcTemplate.query(query, new MapSqlParameterSource(), new RowMapperProducts());
        //SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        //return productsList;

    }

    public static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
