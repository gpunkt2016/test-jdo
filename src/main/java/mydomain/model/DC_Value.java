package mydomain.model;


import java.util.Date;

public class DC_Value { 
	private String stringValue;
    private Date dateValue;          
    
	public DC_Value() {
		super();
	}


	/**
	 * 
	 * @param v
	 */
	public DC_Value(String v){
		super();		
		setValue(v);
	}
	
	
	/**
	 * 
	 * @param v
	 */
	public DC_Value(Date v){
		super();		
		setValue(v);
	}
	
	
	public void setValue(String v){
		if(v == null) return;
		stringValue = v;
	}
	
	public void setValue(Date v){
		if(v == null) return;
		dateValue = v;
	}

	

	/**
	 * @return the stringValue
	 */
	public String getStringValue() {
		return stringValue;
	}



	/**
	 * @return the dateValue
	 */
	public Date getDateValue() {
		return dateValue;
	}

	public Object getValue(){
		if(stringValue != null) return stringValue;	
		if(dateValue != null) return dateValue;	
		return null;		
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		DC_Value dce = new DC_Value();	
		dce.stringValue = stringValue;
		dce.dateValue = dateValue;	
		return dce;
	}
	
	public static DC_Value createDCValue(Object o){
		if(o == null) return null;
		if(o instanceof String){
			return new DC_Value((String) o);
		}else if(o instanceof Date){
			return new DC_Value((Date) o);
		}
		return null;
	}
    
	public static Object getValueOfDCV(DC_Value dcvalue){
		if(dcvalue==null) return null;
		return dcvalue.getValue();
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {		
		 return super.toString();
	}
	
	
}
