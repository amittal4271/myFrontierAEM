
use(function () {
   var connector = sling.getService( Packages.com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector);
    var server = connector.server;
   
    return { 
        server: server
    }; 
});