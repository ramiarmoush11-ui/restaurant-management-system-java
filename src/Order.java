public class Order{
     double tip=0;
     String name;
     double price=0.0;
     int numberrequired =0;
     String typeoforder;
     String status="In preparation";
     String namecustomer;
     public Order(){

     }
    public Order(double tip, String name, double price, int numberrequired,String namecustomer) {
        this.tip = tip;
        this.price = price;
        this.name = name;
        this.numberrequired = numberrequired;
        this.namecustomer=namecustomer;
    }

    @Override
    public String toString() {
        return "Order{" +
                " namecustomer = " + namecustomer + '\'' +
                ", name = '" + name + '\'' +
                ", price = " + price +
                ", numberrequired = " + numberrequired +
                ", tip = " + tip +
                ", typeoforder = '" + typeoforder + '\'' +

                " }";
    }
}
