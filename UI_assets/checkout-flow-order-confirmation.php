<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">
               <div class="container">

                    <section class="account-main-holder checkout-holder order-confirmation-page">
                         <div id="order-confirmation-header">
                              <h1 class="confirmation-header">Order Successful!</h1>
                              <p class="confirmation-text">An order confirmation email has been sent to <span class="confirmation-email">jprahst32@gmail.com</span>.</p>
                              <p class="confirmation-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.</p>
                              <a href="./index.php" class="btn btn-light-green btn-continue-shopping-confirmation">Continue Shopping</a>
                         </div>
                         <div id="order-confirmation-items-holder">
                              <div id="order-confirmation-items-header">
                                   <p class="confirmation-order-id">
                                        For future reference, your Order ID is listed below:<br/>
                                        <span class="confirmation-order-number">Order ID: #123456-67</span>
                                   </p>
                                   <a href="javascript: window.print();" class="btn btn-white-green btn-print-confirmation">Print Order Details</a>
                              </div>
                              <div id="order-confirmation-items-section">
                                   <div class="each-confirmation-item first">
                                        <div class="item-info">
                                             <div class="confirmation-image-holder">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-coarse-grind-sea-salt-349-oz-one-19555-19555-default-0-3692/4a146135b002f372a6a35292bee5bc13.jpg" class="cart-item-image" alt="Frontier Coarse Grind Sea Salt 3.49 oz., One, Size 3.49 Ounce">
                                             </div>
                                             <div class="confirmation-item-details">
                                                  <a href="#" class="confirmation-item-product-name">Frontier Coarse Grind Sea Salt 3.49 oz.</a>
                                                  <span class="confirmation-item-number">Item #19555</span>
                                                  <span class="confirmation-price">$40.30</span>
                                             </div>
                                        </div>
                                        <div class="qty-info">
                                             <span class="confirmation-qty-text">Qty: <span class="confirmation-qty">1</span></span>
                                        </div>
                                   </div>

                                   <div class="each-confirmation-item ">
                                        <div class="item-info">
                                             <div class="confirmation-image-holder">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-coarse-grind-sea-salt-349-oz-one-19555-19555-default-0-3692/4a146135b002f372a6a35292bee5bc13.jpg" class="cart-item-image" alt="Frontier Co-op Aged Cascara Sagrada Bark, Cut and Sifted 1 lb.">
                                             </div>
                                             <div class="confirmation-item-details">
                                                  <a href="#" class="confirmation-item-product-name">Frontier Co-op Aged Cascara Sagrada Bark, Cut &amp; Sifted 1 lb.</a>
                                                  <span class="confirmation-item-number">Item #235522</span>
                                                  <span class="confirmation-price">$55.20</span>
                                             </div>
                                        </div>
                                        <div class="qty-info">
                                             <span class="confirmation-qty-text">Qty: <span class="confirmation-qty">3</span></span>
                                        </div>
                                   </div>

                                   <div class="each-confirmation-item last">
                                        <div class="item-info">
                                             <div class="confirmation-image-holder">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-coarse-grind-sea-salt-349-oz-one-19555-19555-default-0-3692/4a146135b002f372a6a35292bee5bc13.jpg" class="cart-item-image" alt="Frontier Coarse Grind Sea Salt 3.49 oz., One, Size 3.49 Ounce">
                                             </div>
                                             <div class="confirmation-item-details">
                                                  <a href="#" class="confirmation-item-product-name">Frontier Coarse Grind Sea Salt 3.49 oz.</a>
                                                  <span class="confirmation-item-number">Item #19555</span>
                                                  <span class="confirmation-price">$40.30</span>
                                             </div>
                                        </div>
                                        <div class="qty-info">
                                             <span class="confirmation-qty-text">Qty: <span class="confirmation-qty">1</span></span>
                                        </div>
                                   </div>

                                   
                              </div>
                         </div>
                    </section>

               </div>
          </main>

          

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
          $(document).ready(function() {
               console.log('checkout doc ready');
          });
          </script>
     </body>
</html>
