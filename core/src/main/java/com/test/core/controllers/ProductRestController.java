package com.test.core.controllers;

import com.test.core.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/products")
public class ProductRestController {
    private int counter = 4;
//    @Autowired
//    ProductService productService;
//
//    @GetMapping("/products")
//    public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//    }
    public List<Map<String, String>> products = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>(){{put("id","1"); put("Product №1","desc 1");}});
        add(new HashMap<String, String>(){{put("id","2"); put("Product №2","desc 2");}});
        add(new HashMap<String, String>(){{put("id","3"); put("Product №3","desc 3");}});
    }};

    @GetMapping(
        value = "/jackson/{id}",
        produces = "application/xml"
    )
    public Product jacksonXml(@PathVariable String id) {
        Map<String, String> prodFromDb = products.stream().filter(products -> products.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
        String prodStr = prodFromDb.toString().substring(prodFromDb.toString().indexOf(",") + 2, prodFromDb.toString().length());
        String[] parseStr = prodStr.replaceAll("}","").split("=");
        System.out.println(prodStr);
        String name = parseStr[0];
        String desc = parseStr[1];
        return new Product(Integer.parseInt(id), name, desc);
    }

    @PostMapping(
            value = "/jackson/add",
            produces = "application/xml"
    )
    public Product jacksonXmlAdd(@PathVariable String id , @RequestBody Product product) {
        return product;
    }

    @GetMapping(produces ="application/json")
    public List<Map<String, String>> list(){
        return products;
    }

    @GetMapping("{id}")
    public Map<String, String> getProduct(@PathVariable String id){
        return products.stream().filter(products -> products.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }
    @PostMapping()
    public Map<String,String> create(@RequestBody Map<String, String> product){
        product.put("id", String.valueOf(counter++));
        products.add(product);
        return product;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> product){
        Map<String,String> prodFromDb = getProduct(id);
        prodFromDb.putAll(product);
        prodFromDb.put("id", id);
        return prodFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> prod = getProduct(id);
        products.remove(prod);
    }
}
