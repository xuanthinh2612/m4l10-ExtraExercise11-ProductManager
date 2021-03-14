package productManager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import productManager.model.Category;
import productManager.model.Product;

import java.util.List;

// add Generic target class and id dataType
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    // find by id custom by query DB
//    @Query(value = "select * from Product where Product.category_id = ?",nativeQuery = true)
//    List<Product> findAllByCategory(Long id);

    //find by object by using default method
    List<Product> findAllByCategory(Category category);

    @Query(value = "select * from Product where Product.name like ?", nativeQuery = true)
    List<Product> findAllByName(String name);
    @Query (value = "select * from Product  order by dateTime DESC limit 5;",nativeQuery = true)
    List<Product> get5LatestProduct();
    @Query(value = "select * from Product order by price DESC  limit 5", nativeQuery = true)
    List<Product> getTop5expensive();

    @Query(value = "select  sum(price*product.quantity) from product;", nativeQuery = true)
    Long getTotalMoneyRemainInWareHouse();
}
