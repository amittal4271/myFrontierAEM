<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container">
               <?php include(dirname(__FILE__).'/snippets/breadcrumbs.php');?>
               <section id="product-detail">
                    <div class="container">
                         <div id="product-images">
                              <div id="product-thumbnails">
                                   <button type="button" class="each-thumbnail active" data-mainimageswitch="../frontierwholesales/images/pdp-image-1-main.jpg">
                                        <img src="../frontierwholesales/images/pdp-image-1-thumb.jpg"/>
                                   </button>
                                   <button type="button" class="each-thumbnail" data-mainimageswitch="../frontierwholesales/images/pdp-image-2-main.jpg">
                                        <img src="../frontierwholesales/images/pdp-image-2-thumb.jpg"/>
                                   </button>
                                   <button type="button" class="each-thumbnail" data-mainimageswitch="../frontierwholesales/images/pdp-image-3-main.jpg">
                                        <img src="../frontierwholesales/images/pdp-image-3-thumb.jpg"/>
                                   </button>
                              </div>
                              <div id="product-main-image">
                                   <span class="badge badge-sale">Sale</span>
                                   <div class="product-main-image-holder">
                                        <img alt="Image Title" src="../frontierwholesales/images/pdp-image-1-main.jpg"/>
                                   </div>
                              </div>
                         </div>
                         <div id="product-information">
                              <div class="product-sku-number">Item #: 23002</div>

                              <h1 class="product-title-name">Frontier Almond Flavor 4 fl. oz.</h1>
                              <!--
                              <div class="product-icons">
                                   <span class="each-product-icon-holder gluten-free">
                                        <img src="../frontierwholesales/images/gluten-free-icon.png" alt="Gluten Free Product"/>
                                   </span>
                                   <span class="each-product-icon-holder organic">
                                        <img src="../frontierwholesales/images/organic-icon.png" alt="Oranic Product"/>
                                   </span>
                                   <span class="each-product-icon-holder most-popular">
                                        <img src="../frontierwholesales/images/most-popular-icon.png" alt="Popular Product"/>
                                   </span>
                              </div>
                              -->
                              <div class="product-detail-attributes">
                                   
                                   <span class="dt-label">Brand: </span>
                                   <span class="dd-value brand">
                                        <a href="#" class="dd-value-link">Frontier</a>
                                   </span>
                              
                                   <span class="dt-label">UPC: </span>
                                   <span class="dd-value upc">089836230027</span>
                                   
                              </div>

                              <div class="product-price-summary"> 
                                   <span class="product-sale-price">$5.80</span> 
                                   <span class="product-now-price">Now $4.93</span>
                                   <span class="product-save-percent">Save 25%</span>
                              </div>

                              <div class="product-detail-message">Availability: In Stock</div>

                              <div class="pdp-message-holder">
                                   <div class="alert alert-danger" role="alert"> 
                                        <span class="glyphicon glyphicon-exclamation-sign"></span>
                                        <strong>Restricted Shipping Item:</strong> Some type of message that explains the situation.
                                   </div>
                                   <div class="alert alert-bulk" role="alert">
                                        <span class="glyphicon glyphicon-info-sign"></span>
                                        <strong>Buy 5 to save 5%:</strong> Discount price will display in cart.
                                   </div>
                              </div>

                              <div class="product-wishlist">
                                   <button class="btn btn-add-wishlist btn-wishlist">Add to Wishlist +</button>
                                   <div class="add-to-shelf btn">
                                        <select>
                                             <option>Add to Shelves +</option>
                                             <option value="shelf1">Shelf 1</option>
                                             <option value="shelf2">Shelf 2</option>
                                             <option value="shelf3">Shelf 3</option>
                                        </select>
                                   </div>
                              </div>


                              <form action="" method="post" class="product-detail-form-add">
                                   <div class="product-add-options">
                                        <div class="qty-holder">
                                             <button type="button" class="decrease qty-button qty-down">
                                                  <span class="decrease-minus">-</span>
                                                  <span class="visuallyhidden">decrease</span>
                                             </button>
                                             <input type="text" min="1" name="quantity" id="product-qty-input" class="qty-input-field" value="1">
                                             <button type="button" class="increase qty-button qty-up">
                                                  <span class="increase-plus">+</span>
                                                  <span class="visuallyhidden">increase</span>
                                             </button>
                                        </div>
                                        <div class="add-to-cart-holder">
                                             <button class="btn btn-light-green btn-add-to-cart">Add To Cart</button>
                                        </div>
                                   </div>
                              </form>

                              <div class="product-overview">
                                   <h2 class="heading-product-overview">Product Overview</h2>
                                   <p class="description-product-overview">
                                        Simply Organic&reg;'s extra rich and creamy non-alcoholic organic Vanilla Flavor   contains vanilla derived exclusively from Madagascar beans. From baking to   enhancing savory fare, this rich, floral and fruity vanilla flavor captures   of all the complex aroma and flavor essences of fully cured vanilla beans.
                                        <!--<a class="description-read-more" href="#">Read More</a>-->
                                   </p>
                                   <ul class="overview-stat-list">
                                        <li class="each-stat icon-check">
                                             <span class="each-stat-icon">
                                                  <span class="glyphicon glyphicon-ok"></span>
                                             </span>
                                             <span class="each-stat-name-text">Organic</span>
                                        </li>
                                   
                                        <li class="each-stat icon-check">
                                             <span class="each-stat-icon">
                                                  <span class="glyphicon glyphicon-ok"></span>
                                             </span>
                                             <span class="each-stat-name-text">Not Tested On Animals</span>
                                        </li>
                                   
                                        <li class="each-stat icon-check">
                                             <span class="each-stat-icon">
                                                  <span class="glyphicon glyphicon-ok"></span>
                                             </span>
                                             <span class="each-stat-name-text">Non Irradiated</span>
                                        </li>
                                   
                                        <li class="each-stat icon-check">
                                             <span class="each-stat-icon">
                                                  <span class="glyphicon glyphicon-ok"></span>
                                             </span>
                                             <span class="each-stat-name-text">Vegan</span>
                                        </li>
                                   
                                        <li class="each-stat icon-check">
                                             <span class="each-stat-icon">
                                                  <span class="glyphicon glyphicon-ok"></span>
                                             </span>
                                             <span class="each-stat-name-text">Alcohol Free</span>
                                        </li>
                                   </ul>
                              </div>

                              <div class="product-detail-additional-details">
                                   <span class="product-detail-overview-item-detail">
                                        <span class="product-detail-overview-label">Flavor</span>
                                        <span class="product-detail-overview-value">Crunchy Lentil Fest</span>
                                   </span>
                              
                                   <span class="product-detail-overview-item-detail">
                                        <span class="product-detail-overview-label">Size</span>
                                        <span class="product-detail-overview-value">8 oz.</span>
                                   </span>

                                   <span class="product-detail-overview-item-detail">
                                        <span class="product-detail-overview-label">Count</span>
                                        <span class="product-detail-overview-value">1 Item</span>
                                   </span>
                              </div>

                              <div class="product-more-details">
                                   <h3 class="heading-product-overview">Additional Product Details</h3>
                                   <div class="each-additional-section">
                                        <h4 class="additional-heading">Directions</h4>
                                        <p class="additional-description">Soak 6 to 8 hrs. in 4 parts warm water, 1 part seeds, rinse and spread evenly in sprouter. sprouts must be rinsed and drained 2 to 3 times per day and kept from light until the 3 or 4th day. taste test as you grow, warm room temp increases growth. some white fuzz is natural capillary roots, rinse the hulls away by immersing in large bowl of cool water, seperate clumps, let hulls float to top and skim off. process helps revent fermentation. refrigerate in plastic bag after sprouting, use within 2 to 3 days</p>
                                   </div>
                                   <div class="each-additional-section">
                                        <h4 class="additional-heading">Ingredients</h4>
                                        <p class="additional-description">blue french lentils, red and green lentils</p>
                                   </div>
                                   <div class="each-additional-section">
                                        <h4 class="additional-heading">Package Content</h4>
                                        <p class="additional-description">Contains 4 (0.12 oz.) lipsticks each of Blush Basin, Brimming Berry, Fuchsia Flood, Iced Iris, Juniper Water, Lily Lake, Magenta Rush, Nile Nude, Ruby Ripple, Russet River, Scarlet Soaked, Suede Splash, Sunset Cruise and Tulip Tide, and cardboard countertop display</p>
                                   </div>
                              </div>

                         </div>
                    </div>
               </section>

               <section class="related-products-holder" id="related-products-holder-pdp">
                    <div class="container">
                         <div class="related-products-heading-holder">
                              <h4 class="related-products-heading">Related Products</h4>
                         </div>
                         <div id="pdp-scroller">
                              <div class="swiper-container">
                                   <div class="swiper-wrapper">
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-one.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">FoodScience of Vermont B12-MC Chewable Tablets </span>
                                                  </span>
                                             </a>
                                        </div>
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-two.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">Thompson Vitamin D Cholecalciferol 90 tablets</span>
                                                  </span>
                                             </a>
                                        </div>
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-three.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">FoodScience of Vermont B12-MC Chewable Tablets </span>
                                                  </span>
                                             </a>
                                        </div>
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-one.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">FoodScience of Vermont B12-MC Chewable Tablets </span>
                                                  </span>
                                             </a>
                                        </div>
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-two.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">Thompson Vitamin D Cholecalciferol 90 tablets</span>
                                                  </span>
                                             </a>
                                        </div>
                                        <div class="swiper-slide">
                                             <a class="each-slider-related-link-holder" href="#">
                                                  <span class="image-holder">
                                                       <img src="../frontierwholesales/images/pdp-related-three.jpg"/>
                                                  </span>
                                                  <span class="title-holder">
                                                       <span class="product-tile-sku">Item #: 216971</span>
                                                       <span class="product-tile-name">FoodScience of Vermont B12-MC Chewable Tablets </span>
                                                  </span>
                                             </a>
                                        </div>
                                   </div>

                                   <div class="swiper-pagination"></div>

                                   <div class="swiper-button-prev"></div>
                                   <div class="swiper-button-next"></div>
                              </div>
                         </div>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
