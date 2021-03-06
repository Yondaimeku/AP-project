package hibernate;
// Generated Mar 24, 2014 7:27:31 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * DrinkTypes generated by hbm2java
 */
public class DrinkTypes  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set drinkses = new HashSet(0);

    public DrinkTypes() {
    }

	
    public DrinkTypes(String name) {
        this.name = name;
    }
    public DrinkTypes(String name, Set drinkses) {
       this.name = name;
       this.drinkses = drinkses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getDrinkses() {
        return this.drinkses;
    }
    
    public void setDrinkses(Set drinkses) {
        this.drinkses = drinkses;
    }




}


