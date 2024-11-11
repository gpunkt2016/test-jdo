/**********************************************************************
Copyright (c) 2003 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package mydomain.model;

import java.util.Date;
import java.util.HashMap;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Definition of a Book. Extends basic Product class.
 */
@PersistenceCapable
public class Book extends Product
{
    protected String author=null;

    protected String isbn=null;

    protected String publisher=null;
    
    protected Date pubDate = null;
    
    HashMap<String, DC_Value> data = new HashMap<String, DC_Value>();

    public Book(String name, String description, double price, String author, String isbn, String publisher , HashMap<String,DC_Value> data)
    {
        super(name,description,price);
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.data = (HashMap<String,DC_Value>) data.clone();
    }

    public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getAuthor()
    {
        return author;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String toString()
    {
        return "Book : " + author + " - " + name;
    }
    
    public Object getValueOfDCV(String field){    	
	    DC_Value dcValue = (DC_Value) data.get(field);
	    if(dcValue==null) return null;
	    
	    return dcValue.getValue();
    }

    /*
     * Instantiate a DC_Value dependend on value data type.
     * ATTENTION: If value is instance of String, DC_Value will instantiated as String, not as Text-Instance!
     */
	public void setValueOfDCV(String field, Object value) {
		if(field == null) return;
		if(value != null){
			if(value instanceof String){
				//TODO hier muss der Datentyp des Feldes bekannt sein, damit entsprechende Instanz zurückgegeben werden kann (->String oder Text)
				setObject(field, new DC_Value((String) value));
			}else if(value instanceof Date){
				setObject(field, new DC_Value((Date) value));
			}
		}else{
			if(data!=null) data.remove(field);
		}
	}
	
	public void setObject(String field, DC_Value value) {
		//in Vorversion setValue(Field field, DC_Value value);
		if(field == null) return;
		if(data==null) data=new HashMap<String, DC_Value>();
		if(value != null && (value.getValue() != null )){
			data.put(field, value);		
		}else{
			data.remove(field);
		}
	}
}