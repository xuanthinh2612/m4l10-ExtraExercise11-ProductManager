package productManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import productManager.exception.PageNotFoundException;
import productManager.model.Category;
import productManager.model.Product;
import productManager.service.category.ICategoryService;
import productManager.service.product.IProductService;
import productManager.service.product.ProductService;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Validated
@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ProductService productService1;

    @ModelAttribute("categoryList")
    public List<Category> categoryList() {
        return categoryService.findALl();
    }

    @ModelAttribute("category")
    public Category sendCategory() {
        return new Category();
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView showErrors() {
        return new ModelAndView("pageNotFound");
    }

    @GetMapping("/list")
    public ModelAndView showList(@PageableDefault(size = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> productList = productService.findALl(pageable);
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult, Model model) {
//add dateTime to product
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            Date date = new Date();
            product.setDateTime(date);

            productService.save(product);
            return "redirect:/list";

        }

    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) throws PageNotFoundException {
        ModelAndView modelAndView = new ModelAndView("create");

        Product product = productService.findById(id);

        modelAndView.addObject("product", product);
        return modelAndView;


    }

    @PostMapping("/update/{id}")
    public String updateProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult, Model model) {

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute(product);
            return "create";
        } else {
            Date date = new Date();
            product.setDateTime(date);

            productService.save(product);
            return "redirect:/list";
        }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) throws PageNotFoundException {
        ModelAndView modelAndView = new ModelAndView("delete");
        return modelAndView.addObject("product", productService.findById(id));
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/list";
    }

    @PostMapping("/findByName")
    public ModelAndView findByName(@RequestParam String name) {
        String keyWord = "%" + name + "%";
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", productService.findByName(keyWord));
        return modelAndView;
    }

    @PostMapping("/findByCategory")
    public ModelAndView findByCategory(@ModelAttribute Category category) {
        ModelAndView modelAndView = new ModelAndView("list");

        //find by id
        //Long id = category.getId();
        // modelAndView.addObject("productList",  productService1.findAllByCategory(id));

        //find by object using repository default method
        modelAndView.addObject("productList", productService1.findAllByCategory(category));

        return modelAndView;
    }

    @GetMapping("/get5Latest")
    public ModelAndView get5LatestProduct() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", productService1.get5LatestProduct());
        return modelAndView;
    }

    @GetMapping("/getTop5expensive")
    public ModelAndView getTop5expensive() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("productList", productService1.getTop5expensive());
        return modelAndView;
    }

    @GetMapping("/getTotalMoneyRemainInWareHouse")
    public ModelAndView getTotalMoneyRemainInWareHouse() {
        ModelAndView modelAndView = new ModelAndView("totalMoney");
        List<Product> productList = productService.findALl();
        Long totalMoney = productService1.getTotalMoneyRemainInWareHouse();
        modelAndView.addObject("productList",productList);
        modelAndView.addObject("totalMoney",totalMoney);
        return modelAndView;
    }

    @GetMapping("/*")
    public String returnFailURL() throws PageNotFoundException {
        throw new PageNotFoundException();

    }

    @GetMapping("/*/*")
    public String returnFailURL1() throws PageNotFoundException {
        throw new PageNotFoundException();

    }

}
