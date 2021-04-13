package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Product;
import ru.itis.models.Wishlist;

import java.util.List;

public class WishlistRepositoryImpl implements WishlistRepository {

    private final JdbcTemplate template;

    private CategoryRepository categoryRepository;

    //language=SQL
    private final String SQL_FIND_PRODUCTS_BY_USER_ID = "SELECT p.id as p_id, p.name, p.description, p.image_name, p.price, p.category_id FROM user_in_wishlist inner JOIN product p on user_in_wishlist.product_id = p.id INNER JOIN wishlist w on w.id = user_in_wishlist.wishlist_id where user_id=?";

    //language=SQL
    private final String SQL_FIND_BY_USER_ID = "SELECT * FROM wishlist where user_id=?";

    //language=SQL
    private final String SQL_SAVE_BY_USER = "INSERT INTO wishlist (user_id) VALUES (?)";

    //language=SQL
    private final String SQL_INSERT_PRODUCT = "INSERT INTO user_in_wishlist (wishlist_id, product_id) VALUES (?, ?)";

    //language=SQL
    private final String SQL_DELETE_PRODUCT = "DELETE FROM user_in_wishlist WHERE product_id=? AND wishlist_id=?";

    private RowMapper<Product> productRowMapper = (r, i) -> Product.builder()
            .id(r.getLong("p_id"))
            .name(r.getString("name"))
            .description(r.getString("description"))
            .imageName(r.getString("image_name"))
            .price(r.getDouble("price"))
            .category(categoryRepository.findById(r.getLong("category_id")))
            .build();

    private RowMapper<Wishlist> wishlistRowMapper = (r, i)  -> Wishlist.builder()
            .id(r.getLong("id"))
            .build();

    public WishlistRepositoryImpl(JdbcTemplate template, CategoryRepository categoryRepository) {
        this.template = template;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Wishlist findByUserId(long id) {
        try {
            Wishlist wishlist = template.queryForObject(SQL_FIND_BY_USER_ID, wishlistRowMapper, id);
            System.out.println(wishlist);
            List<Product> wishlistProducts = template.query(SQL_FIND_PRODUCTS_BY_USER_ID, productRowMapper, id);
            System.out.println(wishlistProducts);
            wishlist.setProducts(wishlistProducts);
            return wishlist;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveByUser(long id) {
        template.update(SQL_SAVE_BY_USER, id);
    }

    @Override
    public void addProduct(Product product, long id) {
        template.update(SQL_INSERT_PRODUCT, id, product.getId());
    }

    @Override
    public void deleteProduct(Product product, long id) {
        template.update(SQL_DELETE_PRODUCT, product.getId(), id);
    }
}
