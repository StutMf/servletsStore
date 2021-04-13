package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Product;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate template;

    private CategoryRepository categoryRepository;

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM product";

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM product where id=?";

    //language=SQL
    private final String SQL_DELETE = "DELETE FROM product where id=?";

    //language=SQL
    private final String SQL_SAVE = "INSERT INTO product (name, description, price, image_name, category_id) VALUES (?,?,?,?,?)";

    //language=SQL
    private final String SQL_FIND_BY_CATEGORY = "SELECT * FROM product WHERE category_id=?";

    private RowMapper<Product> productRowMapper = (r, i) -> Product.builder()
            .id(r.getLong("id"))
            .name(r.getString("name"))
            .description(r.getString("description"))
            .imageName(r.getString("image_name"))
            .price(r.getDouble("price"))
            .category(categoryRepository.findById(r.getLong("category_id")))
            .build();

    public ProductRepositoryImpl(JdbcTemplate template, CategoryRepository categoryRepository) {
        this.template = template;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        try {
            return template.query(SQL_FIND_ALL, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(Product product) {
        template.update(SQL_SAVE, product.getName(), product.getDescription(), product.getPrice(), product.getImageName(), product.getCategory().getId());
    }

    @Override
    public Product findById(Long id) {
        try {
            return template.queryForObject(SQL_FIND_BY_ID, productRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        template.update(SQL_DELETE, id);
    }

    @Override
    public List<Product> findByCategory(long categoryId) {
        try {
            return template.query(SQL_FIND_BY_CATEGORY, productRowMapper, categoryId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
