
use(function () {
    var connector = sling.getService( Packages.com.frontierwholesales.core.magento.services.MagentoCommerceConnectorService);

    var server = connector.getServer();
   
    return { 
        server: server
    }; 
});