package eu.epitech.epicture;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Louis Giraud on 11/02/2018.
 */
public class CardImageTest {

    @Test
    public void getUrl() throws Exception {
        CardImage card = new CardImage("http://google.com", 1);
        assertEquals("http://google.com", card.getUrl());
    }

    @Test
    public void setUrl() throws Exception {
        CardImage card = new CardImage("http://google.com", 1);
        card.setUrl("http://yahoo.fr");
        assertEquals("http://yahoo.fr", card.getUrl());
    }

    @Test
    public void getFavorite() throws Exception {
        CardImage card = new CardImage("http://google.com", 1);
        assertEquals(1, card.getFavorite());
    }

}