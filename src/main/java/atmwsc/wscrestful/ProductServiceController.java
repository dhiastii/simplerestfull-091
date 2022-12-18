/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atmwsc.wscrestful;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static{
       //memanggil class product
        Product honey = new Product();
        //memanggil variabel pada class product
        honey.setId("1");
        honey.setName("Honey");
        honey.setJumlah("20");
        honey.setHarga("20000");
        productRepo.put(honey.getId(), honey);
   
        
       //memanggil class product
        Product almond = new Product();
        //memanggil variabel pada class product
        almond.setId("2");
        almond.setName("Almond");
        almond.setJumlah("10");
        almond.setHarga("30000");
        productRepo.put(almond.getId(), almond);
   }
   
   //membuat code untuk menampilkan variabel dengan GET
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
  
   //membuat code untuk memasukkan data baru dengan POST
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product){
        if(productRepo.containsKey(product.getId())){ 
            return new ResponseEntity<>("Id Product sudah ada, silakan masukkan Id yang lain!", HttpStatus.OK); 
        }
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
        }    
   }

   //membuat code untuk mengedit data (id sudah ada) dengan PUT
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
       if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Id Product belum ada, silakan edit Id yang sudah ada!", HttpStatus.NOT_FOUND);
       }
       else{
       productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
       }
    }   
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      productRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
    }
