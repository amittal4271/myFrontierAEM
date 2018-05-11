<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container">
               <section id="breadcrumbs">
                    <div class="container">
                         <ol class="breadcrumb">
                              <li><a href="#">Home</a></li>
                              <li><a href="#">Shop</a></li>
                              <li><a href="#">Grocery</a></li>
                              <li class="active">Beverages</li>
                         </ol>
                    </div>
               </section>

               <div id="mobile-filters-holder">
                    <div id="mobile-filters-header">
                         <div id="mobile-filters-heading">
                              <span>Filters</span>
                         </div>
                         <div id="mobile-filters-close-holder">
                              <button class="btn" id="close-filters-plp-search">
                                   <span class="hidden">Close</span>
                                   <span class="glyphicon glyphicon-remove"></span>
                              </button>
                         </div>
                    </div>
                    <!-- the HTML from id plp-search-left-nav-filters is injected here when opened on smaller devices -->
                    <div id="mobile-filters">
                    </div>
               </div>
               <div id="mobile-filters-overlay"></div>

               <article class="product-list-page">
                    <div class="container">
                         <aside id="plp-search-left-nav-filters">
                              <div class="each-filter-section categories">
                                   <h2 class="filter-heading">Categories</h2>
                                   <ul class="each-filters-list">
                                        <li class="filter-item">
                                             <a href="#" class="filter-link"><span class="text">Aloe Vera</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link"><span class="text">Coffee</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link"><span class="text">Hot Cocoa</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link"><span class="text">Powdered Drink Mixes</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link"><span class="text">Tea</span></a>
                                        </li>
                                        <!-- we only show the first 5 filters for each section -->
                                        <li class="filter-item visuallyhidden">
                                             <a href="#" class="filter-link"><span class="text">Flavors and Syrups</span></a>
                                        </li>
                                        <li class="filter-item filter-item-expand">
                                             <button type="button" class="btn btn-view-more-filters">
                                                  <span class="view-more-text">View More</span>
                                             </button>
                                        </li>
                                   </ul>
                              </div>

                              <div class="each-filter-section brand">
                                   <h2 class="filter-heading">Brand</h2>
                                   <ul class="each-filters-list">
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link selected-filter"><span class="text">Accessories</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Seasonings</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Choice Teas</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Cusa Teas</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Earth Mama Angel Baby</span></a>
                                        </li>
                                        <!-- we only show the first 5 filters for each section -->
                                        <li class="filter-item visuallyhidden">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Frontier</span></a>
                                        </li>
                                        <li class="filter-item visuallyhidden">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Herbal Zap</span></a>
                                        </li>
                                        <li class="filter-item visuallyhidden">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">Mate Factor</span></a>
                                        </li>
                                        <li class="filter-item filter-item-expand">
                                             <button type="button" class="btn btn-view-more-filters">
                                                  <span class="view-more-text">View More</span>
                                             </button>
                                        </li>
                                   </ul>
                              </div>

                              <div class="each-filter-section brand">
                                   <h2 class="filter-heading">Price</h2>
                                   <ul class="each-filters-list">
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">$0-$25</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link selected-filter"><span class="text">$25-$50</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">$50-$75</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">$75-$100</span></a>
                                        </li>
                                        <li class="filter-item">
                                             <a href="#" class="filter-link checkbox-link"><span class="text">$100+</span></a>
                                        </li>
                                   </ul>
                              </div>

                         </aside>
                         <section id="plp-search-product-grid">
                              
                              <div id="plp-search-header-holder" class="search-header-holder">
                                   <div id="search-heading">
                                        <h1 class="plp-search-text-heading">Search Results for: "Food"</h1>
                                        <span class="showing-number-of-items">
                                             Showing <span class="number">28</span> of <span class="number">180</span> Products
                                        </span>
                                   </div>
                                   <div id="search-sort-items">
                                        <div id="filters-holder">
                                             <div class="form-group">
                                                  <label for="sortby-type">Filters</label>
                                                  <button type="button" class="btn btn-black btn-filters-plp-search">View Options</button>
                                             </div>
                                        </div>
                                        <div class="sort-by-holder">
                                             <div class="form-group">
                                                  <label for="sortby-type">Sort By</label>
                                                  <select name="sortby-type" id="sortby-type" class="form-control">
                                                       <option value="/shop/grocery/spices-and-seasonings/?sort=featured">Featured</option>
                                                       <option value="/shop/grocery/spices-and-seasonings/?sort=new">New Arrivals</option>
                                                       <option value="/shop/grocery/spices-and-seasonings/?sort=low">Lowest Price</option>
                                                       <option value="/shop/grocery/spices-and-seasonings/?sort=high">Highest Price</option>
                                                  </select>
                                             </div>
                                        </div>
                                        <div class="items-per-page-holder">
                                             <div class="form-group">
                                                  <label for="sortby-items">Items Per Page:</label>
                                                  <select name="sortby-items" id="sortby-items" class="form-control">
                                                       <option value="/shop/grocery/spices-and-seasonings/?show=28">28</option>
                                                       <option value="/shop/grocery/spices-and-seasonings/?show=56">56</option>
                                                       <option value="/shop/grocery/spices-and-seasonings/?show=100">100</option>
                                                  </select>
                                             </div>
                                        </div>
                                   </div>
                              </div>

                              <div id="product-grid">

                                   <div class="product-grid-row">
                                        <div class="product-grid-item">
                                             <span class="badge badge-sale">Sale</span>
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-1.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">230965</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Black Aerolatte Milk Frother with Stand 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$5.80</span> 
                                                       <span class="product-now-price">Now $4.93</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-6.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Bright Day Brew Ground Coffee 12 oz.
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$7.35</span> 
                                                       <span class="product-now-price"> Now $5.88</span>
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-danger" role="alert"> 
                                                       <span class="glyphicon glyphicon-exclamation-sign"></span>
                                                       <strong>Restricted Shipping</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-8.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Organic Breakfast Blend Decaf Whole Bean Coffee 5 lb. 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$55.35</span> 
                                                  </span>
                                             </a>

                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-2.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">2579</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Frontier Co-op Licorice Root, Cut & Sifted, Organic 1 lb 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$13.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-bulk" role="alert">
                                                       <span class="glyphicon glyphicon-info-sign"></span>
                                                       <strong>Buy 5 to save 5%:</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>
                                   </div>

                                   <div class="product-grid-row">
                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-3.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">230965</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Black Aerolatte Milk Frother with Stand 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$5.80</span> 
                                                       <span class="product-now-price">Now $4.93</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <span class="badge badge-new">New</span>
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-4.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Bright Day Brew Ground Coffee 12 oz.
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$7.35</span> 
                                                       <span class="product-now-price"> Now $5.88</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>

                                        <div class="product-grid-item">
                                             <span class="badge badge-new">New</span>
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-5.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Organic Breakfast Blend Decaf Whole Bean Coffee 5 lb. 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$55.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-9.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">2579</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Frontier Co-op Licorice Root, Cut & Sifted, Organic 1 lb 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$13.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-bulk" role="alert">
                                                       <span class="glyphicon glyphicon-info-sign"></span>
                                                       <strong>Buy 5 to save 5%:</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>
                                   </div>

                                   <div class="product-grid-row">
                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-3.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">230965</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Black Aerolatte Milk Frother with Stand 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$5.80</span> 
                                                       <span class="product-now-price">Now $4.93</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-4.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Bright Day Brew Ground Coffee 12 oz.
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$7.35</span> 
                                                       <span class="product-now-price"> Now $5.88</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-5.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Organic Breakfast Blend Decaf Whole Bean Coffee 5 lb. 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$55.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-9.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">2579</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Frontier Co-op Licorice Root, Cut & Sifted, Organic 1 lb 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$13.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-bulk" role="alert">
                                                       <span class="glyphicon glyphicon-info-sign"></span>
                                                       <strong>Buy 5 to save 5%:</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>
                                        
                                        <div class="every-two-divider"></div>
                                   </div>

                                   <div class="product-grid-row">
                                        <div class="product-grid-item">
                                             <span class="badge badge-sale">Sale</span>
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-1.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">230965</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Black Aerolatte Milk Frother with Stand 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$5.80</span> 
                                                       <span class="product-now-price">Now $4.93</span>
                                                  </span>
                                             </a>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-6.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Bright Day Brew Ground Coffee 12 oz.
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price">$7.35</span> 
                                                       <span class="product-now-price"> Now $5.88</span>
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-danger" role="alert"> 
                                                       <span class="glyphicon glyphicon-exclamation-sign"></span>
                                                       <strong>Restricted Shipping</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-8.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">232331</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Equal Exchange Organic Breakfast Blend Decaf Whole Bean Coffee 5 lb. 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$55.35</span> 
                                                  </span>
                                             </a>

                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="product-grid-item">
                                             <a href="#" class="grid-item-link">
                                                  <span class="grid-image-holder">
                                                       <img src="../frontierwholesales/images/search-result-item-2.jpg" alt="Product Name"/>
                                                  </span>
                                                  <span class="grid-item-number">
                                                       Item # <span class="number">2579</span>
                                                  </span>
                                                  <span class="grid-product-name">
                                                       Frontier Co-op Licorice Root, Cut & Sifted, Organic 1 lb 
                                                  </span>
                                                  <span class="grid-product-price">
                                                       <span class="product-sale-price normal-price">$13.35</span> 
                                                  </span>
                                             </a>
                                             <div class="grid-alert">
                                                  <div class="alert alert-bulk" role="alert">
                                                       <span class="glyphicon glyphicon-info-sign"></span>
                                                       <strong>Buy 5 to save 5%:</strong>
                                                  </div>
                                             </div>
                                             <div class="grid-add-to-cart">
                                                  <button type="button" class="btn btn-light-green btn-grid-add-to-cart">Add to Cart</button>
                                             </div>
                                        </div>

                                        <div class="every-two-divider"></div>
                                   </div>

                              </div>

                              <div id="search-pagination">
                                   <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                             <li>
                                                  <a class="pagination-previous pagination-arrow disabled" href="#" aria-label="Previous">
                                                       <span class="glyphicon glyphicon-triangle-left"></span>
                                                  </a>
                                             </li>
                                             <li class="pagination-numbers">
                                                  <span class="pag-number">1 of 27</span>
                                             </li>
                                             <li>
                                                  <a class="pagination-next pagination-arrow" href="#" aria-label="Next">
                                                       <span class="glyphicon glyphicon-triangle-right"></span>
                                                  </a>
                                             </li>
                                        </ul>
                                   </nav>
                              </div>

                              <div id="plp-search-article-grid">
                                   <div class="search-header-holder article-header-holder">
                                        <div id="search-heading">
                                             <h1 class="plp-search-text-heading">Articles/Posts for: "Food"</h1>
                                             <span class="showing-number-of-items">
                                                  Showing <span class="number">6</span> of <span class="number">10</span> Posts
                                             </span>
                                        </div>
                                   </div>

                                   <div id="article-grid" class="search-results-article-grid match-height">

                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-one.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">March 30, 2018</span>
                                                            <span class="article-title">Your Patronage is Coming: What Are Your Options?</span>
                                                            <span class="article-description">Your options as a member-owner of the cooperative offer a win-win choice of either accepting your share of...</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>
                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-three.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">April 01, 2018</span>
                                                            <span class="article-title">Celebrating Earth Day, Every Day</span>
                                                            <span class="article-description">We’ve found six ways to incorporate love for the Earth into your everyday activities.</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>
                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-five.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">October 23, 2017</span>
                                                            <span class="article-title">The Holidays are Coming  – Is Your Store Ready?</span>
                                                            <span class="article-description">Be sure to visit to our site on Friday, November 24 and Monday, November 27 for special discounts on your...</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>


                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-four.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">March 30, 2018</span>
                                                            <span class="article-title">Your Patronage is Coming: What Are Your Options?</span>
                                                            <span class="article-description">Your options as a member-owner of the cooperative offer a win-win choice of either accepting your share of...</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>
                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-two.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">April 01, 2018</span>
                                                            <span class="article-title">Celebrating Earth Day, Every Day</span>
                                                            <span class="article-description">We’ve found six ways to incorporate love for the Earth into your everyday activities.</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>
                                        <article class="article-grid-item">
                                             <figure class="figure-article-holder">
                                                  <a class="article-link-holder" href="#">
                                                       <div class="article-grid-image">
                                                            <img src="../frontierwholesales/images/blog-image-one.jpg" alt="Alt Image Description"/>
                                                       </div>
                                                       <figcaption class="figcaption-article-grid-info">
                                                            <span class="article-date">October 23, 2017</span>
                                                            <span class="article-title">The Holidays are Coming  – Is Your Store Ready?</span>
                                                            <span class="article-description">Be sure to visit to our site on Friday, November 24 and Monday, November 27 for special discounts on your...</span>
                                                            <span class="btn btn btn-white-green btn-article-read-more">Read More</span>
                                                       </figcaption>
                                                  </a>
                                             </figure>
                                        </article>

                                   </div>
                              </div>
                              

                         </section>

                         

                    </div>
               </article>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
