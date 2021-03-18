package registery.model;

public class Customers {
	
 private String Id;
 private String DateLight;
 private String NameBym;
 private String PriceBym;
 

 
 public Customers(String nameBym) {
	 this.NameBym = nameBym;
 }

 public Customers(String dateLight, String priceBym) {
	 this.PriceBym = priceBym;
	 this.DateLight = dateLight;
 };
 
public Customers(String id, String dateLight, String nameBym, String priceBym) {
	this.Id = id;
	this.DateLight = dateLight;
	this.NameBym = nameBym;
	this.PriceBym = priceBym;
	
}
public String getId() {
	return Id;
}
public void setId(String id) {
	Id = id;
}
public String getDateLight() {
	return DateLight;
}
public void setDateLight(String dateLight) {
	DateLight = dateLight;
}
public String getNameBym() {
	return NameBym;
}
public void setNameBym(String nameBym) {
	NameBym = nameBym;
}
public String getPriceBym() {
	return PriceBym;
}
public void setPriceBym(String priceBym) {
	PriceBym = priceBym;
}
}
