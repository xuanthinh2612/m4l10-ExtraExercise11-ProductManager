package productManager.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import productManager.exception.PageNotFoundException;
import productManager.model.Category;
import productManager.model.Product;
import productManager.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findALl() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findALl(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) throws PageNotFoundException {

        Product product = productRepository.findOne(id);
        if (product==null){
            throw new PageNotFoundException();
        }
         return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }
//find by id using custom method
//    public List<Product> findAllByCategory(Long id) {
//        return productRepository.findAllByCategory(id);
//    }
    //fid by object using default method
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> get5LatestProduct(){
        return productRepository.get5LatestProduct();
    }
    public List<Product> getTop5expensive(){
        return productRepository.getTop5expensive();
    }
    public Long getTotalMoneyRemainInWareHouse(){
        return productRepository.getTotalMoneyRemainInWareHouse();
    }

}
