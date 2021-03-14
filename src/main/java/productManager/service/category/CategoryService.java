package productManager.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import productManager.model.Category;
import productManager.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService  implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> findALl() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Page<Category> findALl(Pageable pageable) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Category> findByName(String name) {
        return null;
    }
}
