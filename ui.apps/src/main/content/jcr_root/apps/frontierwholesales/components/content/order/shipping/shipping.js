use(function () {
    //var jsonParam = $this.cartObject;
   // out.println("json param is "+request.getAttribute("CartObject"));
    var cartObjects={};
    var dGrandTotal = Packages.java.text.DecimalFormat("#0.00");
   var  jsonParam = JSON.parse(request.getAttribute("CartObject"));
    cartObjects['qty']=jsonParam.items_qty;
    cartObjects['subtotal']= dGrandTotal.format(jsonParam.grand_total);
    //This needs to be changed based tax & discounts
    cartObjects['grand_total'] = dGrandTotal.format(jsonParam.grand_total);
    return cartObjects;
    
});