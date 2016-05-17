/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hirsiserver;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author hellsten
 */
@Path("words")
public class WordsResource {

    @Context
    private UriInfo context;

    static private WordsContainer mwc = new WordsContainer();
    
    public WordsResource() 
    {
    }

    @GET
    @Path("getWord")
    @Produces("application/json")
    public JsonValue getJson() 
    {
        int id = mwc.getRandomWordId();
        JsonValue json = Json.createObjectBuilder()
                .add("id", id)
                .add("length", mwc.getWordLength(id)).build();
        
        return json;
    }

    
    @GET
    @Path("word/{id}")
    @Produces("text/plain")
    public String getWord(@PathParam("id")int id) 
    {
        
        return mwc.getWord(id);
    }
  
    
    @GET
    @Path("wordcount")
    @Produces("text/plain")
    public String getWordCount() 
    {
        return Integer.toString(mwc.getWordsCount());
    }
  
    
    @GET
    @Path("letterInWord/{id}")
    @Produces("application/json")
    public JsonValue getLetterInWord(@PathParam("id")int id, @QueryParam("letter")String letter) 
    {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        if(letter != null && letter.length() == 1)
        {
            String word = mwc.getWord(id);
            char c = letter.toUpperCase().charAt(0);

            for(int i = 0; i < word.length(); ++i)
            {
                if( word.charAt(i) == c )
                {
                    jsonBuilder.add( Integer.toString(i), letter );
                }
            }
        }
        
        return jsonBuilder.build();
    }
}
