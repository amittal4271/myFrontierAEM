use(function () {
    // response.sendRedirect("/content/frontierwholesales/en/pdp.html?sku=WP09-28-Black");
    var connector = sling.getService( Packages.com.frontierwholesales.core.magento.services.FrontierWholesalesMagentoCommerceConnector);
    var searchTerm = request.getParameter("q");
    var products = {};

    if(searchTerm != null) {
        var search = new Packages.com.frontierwholesales.core.beans.search.MagentoSearch();
		var searchTerm = "%" + searchTerm + "%";

		var filter = new Packages.com.frontierwholesales.core.beans.search.MagentoSearchFilterGroup();
		filter.addFilterCondition(new Packages.com.frontierwholesales.core.beans.search.MagentoSearchCondition("botanicalname", searchTerm, "like"));
		filter.addFilterCondition(new Packages.com.frontierwholesales.core.beans.search.MagentoSearchCondition("name", searchTerm, "like"));
		search.addFilter(filter);
        search.setCurrentPage(1);
        search.setPageSize(20);

        var token = connector.getAdminToken();
        var products = connector.getProducts(token, search);

        // products = JSON.parse(products);
    }

    // log.info("Search returned: " + products.items[0].sku);
    
    return { products: products }; 
});