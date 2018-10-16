package test1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SmoothieTest {
    @Test
    public void classicSmoothie() {
        assertEquals("banana,honey,mango,peach,pineapple,strawberry",
                     Smoothie.ingredients("Classic"));
    }

    @Test
    public void classicSmoothieWithoutStrawberry() {
        assertEquals("banana,honey,mango,peach,pineapple",
                     Smoothie.ingredients("Classic,-strawberry"));
    }    

    @Test
    public void classicSmoothieWithoutStsrawberry() {
        assertEquals("banana,honey,mango,peach,strawberry",
                     Smoothie.ingredients("Classic,-pineapple"));
    }  
}