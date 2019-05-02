package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier perfectBunnies = new Supplier("Perfect Bunnies", "I am a hobbyist breeder of these wonderful little creatures. Our rabbits that we use for breeding are our family pets and it is purely for enjoyment that we do it. My rabbits have completely taken over my garden and I have a natural love for rabbits and spend all my spare time looking after them!");
        supplierDataStore.add(perfectBunnies);
        Supplier blueCloverRabbitry = new Supplier("Blue Clover Rabbitry", "We are blessed to live here in the Pacific Northwest! We currently have an acre of land where we raise Holland lop bunnies, bees, and chickens. We are located in Kent Washington which is about 25 minutes south of Seattle");
        supplierDataStore.add(blueCloverRabbitry);
        Supplier bethanysBunnies = new Supplier("Bethany's Bunnies", "I am based in Memphis, TN, near Hwy 385 and Riverdale. I have been breeding bunnies for over 18 years. I am currently only breeding Mini Rex, Holland Lops, English Spots and Continental Giants");
        supplierDataStore.add(bethanysBunnies);
        Supplier backyardBunnyBreeder = new Supplier("Backyard Bunny Breeder", "I've been breeding rabbits for 3 years now. I have 5 breeders rabbits and sell all of the kits when they are of age. My breeders range from pure Flemish Giant, to New Zealand and Flemish mix. Each of my rabbits are between 10-15 lbs. All in good health.");
        supplierDataStore.add(backyardBunnyBreeder);
        Supplier nyulmentes = new Supplier("Bunny Rescue", "Kis csapatunk igyekszik a bajba jutott, elszökött, rosszul tartott nyuszikat megmenteni, illetve súlyos okok miatt gazdát kereső nyusziknak segíteni.");
        supplierDataStore.add(nyulmentes);

        //setting up a new product category
        ProductCategory alaska = new ProductCategory("Alaska", "animal", "Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.");
        productCategoryDataStore.add(alaska);
        ProductCategory angora = new ProductCategory("Angora","animal", "The Angora rabbit (Turkish: Ankara tavşanı), which is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool, that are gathered by shearing, combing, or plucking.");
        productCategoryDataStore.add(angora);
        ProductCategory californian = new ProductCategory("Californian", "animal", "The Californian, also known as the California White, is a breed of domestic rabbit originally developed for the fur and meat industries by George S. West of Lynnwood, California, starting in 1923.");
        productCategoryDataStore.add(californian);
        ProductCategory hollandlop = new ProductCategory("Holland Lop", "animal", "Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands' Governing Rabbit Council in 1984");
        productCategoryDataStore.add(hollandlop);
        ProductCategory lionhead = new ProductCategory("Lionhead", "animal", "The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone. Their ears are not to exceed 3 inches long.");
        productCategoryDataStore.add(lionhead);


        //setting up products and printing it
        productDataStore.add(new Product("Black Alaska", 49, "USD", "Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.", alaska, backyardBunnyBreeder));
        productDataStore.add(new Product("Brown Alaska", 47, "USD", "Despite its name, the Alaska Rabbit originates in Germany, rather than Alaska.", alaska, bethanysBunnies));
        productDataStore.add(new Product("Grey Angora", 289, "USD", "The Angora rabbit is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool, that are gathered by shearing, combing, or plucking.", angora, perfectBunnies));
        productDataStore.add(new Product("White Angora", 389, "USD", "The Angora rabbit is one of the oldest types of domestic rabbit, is bred for the long fibers of its coat, known as Angora wool, that are gathered by shearing, combing, or plucking.", angora, blueCloverRabbitry));
        productDataStore.add(new Product("Californian", 1, "USD", "The Californian, also known as the California White, is a breed of domestic rabbit originally developed for the fur and meat industries by George S. West of Lynnwood, California, starting in 1923.", californian, nyulmentes));
        productDataStore.add(new Product("Brown Holland Lop", 189, "USD", "Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands' Governing Rabbit Council in 1984", hollandlop, bethanysBunnies));
        productDataStore.add(new Product("Black and White Holland Lop", 1, "USD", "Holland Lop is a breed of domestic rabbit that was recognized by the American Rabbit Breeders Association in 1979 and by the Netherlands' Governing Rabbit Council in 1984", hollandlop, nyulmentes));
        productDataStore.add(new Product("Brown Lionhead", 89, "USD", "The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone. Their ears are not to exceed 3 inches long.", lionhead, backyardBunnyBreeder));
        productDataStore.add(new Product("Brown and Grey Lionhead", 89, "USD", "The Lionhead rabbit has a small, compact body, and the head is bold, yet not quite round from all sides, with well-developed muzzle. Their legs are of medium length and they are of medium bone. Their ears are not to exceed 3 inches long.", lionhead, perfectBunnies));

    }
}
