<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">
               <div class="container">
                    <div id="cart-message-holder" class="global-server-side-message-holder">
                         <div class="alert alert-success" role="alert"> <strong>Free shipping</strong> on orders over $250 ($400 for Alaska and Hawaii) </div>
                    </div>
                    <section class="cart-holder cart-actions">
                         <div class="cart-header-holder no-border">
                              <h1 class="cart-heading">Cart Actions</h1>
                         </div>
                         <div id="cart-actions-grid">
                              <div class="each-action-holder cart-action-status">
                                   <p class="action-title">
                                        <span class="text-label">Cart Status:</span> 
                                        <span class="text-value approved"><span class="glyphicon glyphicon-ok"></span> Approved</span>
                                   </p>

                                   <div class="toggle-action-holder">
                                        <button type="button" class="btn btn-link btn-toggle-cart-actions">
                                             <span class="text">More Cart Actions</span>
                                             <span class="glyphicon glyphicon-menu-up"></span>
                                        </button>
                                   </div>
                              </div>
                              
                              <div class="each-action-holder cart-action-cart-name toggle-holder">
                                   <p class="action-title">
                                        <span class="text-label">Your Cart Name</span>
                                   </p>
                                   <div class="action-holder">
                                        <a href="#" class="btn btn-dark-orange btn-rename-cart" data-toggle="modal" data-target="#modalRenameCart">Rename Cart</a>
                                   </div>
                              </div>

                              <div class="each-action-holder cart-action-new-cart toggle-holder">
                                   <p class="action-title">
                                        <span class="text-label">New Cart</span>
                                   </p>
                                   <div class="action-holder">
                                        <a href="#" class="btn btn-white-green btn-new-order">Create a New Cart</a>
                                   </div>
                              </div>

                              <div class="each-action-holder toggle-holder cart-action-view-other create-new-cart-holder last">
                                   <div class="form-group width-half">
                                        <label for="cart_switch_select">View Other Carts</label>
                                        <select id="cart_switch_select" class="form-control" name="cart_id">
                                             <option value="">Select a Cart</option>
                                             <option value="">Justin's Spiced Cart - Draft</option>
                                             <option value="" disabled>──────────</option>
                                             <option value="">Joe's Cart Created on 3/10/2018 - Draft</option>
                                             <option value="">Svetlana's Coffee Cart - Approved</option>
                                        </select>
                                   </div>
                              </div>

                         </div>
                    </section>
                    <section class="cart-holder">
                         <div class="cart-header-holder table-below">
                              <h1 class="cart-heading">Justin's Cart <span class="normal">- 19 Items</span></h1>
                         </div>

                         <div class="cart-grid">
                              <div class="row-cart-item">
                                   <div class="cart-product-section">
                                        <div class="cart-item-holder">
                                             <a href="#" class="cart-item-link">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-coarse-grind-sea-salt-349-oz-one-19555-19555-default-0-3692/4a146135b002f372a6a35292bee5bc13.jpg" class="cart-item-image" alt="Frontier Coarse Grind Sea Salt 3.49 oz., One, Size 3.49 Ounce">
                                             </a>
                                             <div class="cart-item-details">
                                                  <a href="#" class="cart-item-product-name">Frontier Coarse Grind Sea Salt 3.49 oz.</a>
                                                  
                                                  <span class="cart-item-number">Item #19555</span>
                                                  <a href="#" class="cart-item-wishlist-link">Move to Wishlist</a>
                                             </div>
                                        </div>
                                   </div>
                                   <div class="cart-notification-section">
                                        <span class="cart-message cart-savings-bulk">Your Bulk buy saved you 5%</span>
                                   </div>
                                   <div class="cart-qty-section">
                                        <span class="cart-qty-label">Qty.</span>
                                        <input type="number" min="1" name="item7496674" value="9" class="cart-qty-input form-control">
                                   </div>
                                   <div class="cart-item-break-section"></div>
                                   <div class="cart-price-section">
                                        <span class="cart-price-total-text unit-price">Unit Price:</span>
                                        <span class="cart-grid-sale">
                                             <span class="price-sale">$4.77</span> <span class="price-sale-now">Now $4.53</span>
                                        </span>
                                   </div>
                                   <div class="cart-price-total-section">
                                        <span class="cart-price-total-text">Total:</span> 
                                        <span class="cart-price-number-total">$40.77</span>
                                   </div>
                                   <div class="cart-remove-section">
                                        <a href="#" class="btn btn-only-green-icon" title="Remove">
                                             <span class="action-link-text">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </a>
                                   </div>
                              </div>

                              <div class="row-cart-item">
                                   <div class="cart-product-section">
                                        <div class="cart-item-holder">
                                             <a href="#" class="cart-item-link">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-co-op-aged-cascara-sagrada-bark-cut-sifted-1-lb-one-527-527-default-0-8ced/60606ac2bfe143e0437fd45f7447903b.jpg" class="cart-item-image" alt="Frontier Coarse Grind Sea Salt 3.49 oz., One, Size 3.49 Ounce">
                                             </a>
                                             <div class="cart-item-details">
                                                  <a href="#" class="cart-item-product-name">Frontier Co-op Aged Cascara Sagrada Bark, Cut & Sifted 1 lb.</a>
                                                  
                                                  <span class="cart-item-number">Item #527</span>
                                                  <a href="#" class="cart-item-wishlist-link">Move to Wishlist</a>
                                             </div>
                                        </div>
                                   </div>
                                   <div class="cart-notification-section">
                                        <span class="cart-message cart-savings-bulk">Your Bulk buy saved you 5%</span>
                                   </div>
                                   <div class="cart-qty-section">
                                        <span class="cart-qty-label">Qty.</span>
                                        <input type="number" min="1" name="item7496674" value="9" class="cart-qty-input form-control">
                                   </div>
                                   <div class="cart-item-break-section"></div>
                                   <div class="cart-price-section">
                                        <span class="cart-price-total-text unit-price">Unit Price:</span>
                                        <span class="cart-grid-sale">
                                             <span class="price-sale">$8.25</span> <span class="price-sale-now">Now $7.84</span>
                                        </span>
                                   </div>
                                   <div class="cart-price-total-section">
                                        <span class="cart-price-total-text">Total:</span> 
                                        <span class="cart-price-number-total">$80.40</span>
                                   </div>
                                   <div class="cart-remove-section">
                                        <a href="#" class="btn btn-only-green-icon" title="Remove">
                                             <span class="action-link-text">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </a>
                                   </div>
                              </div>

                              <div class="row-cart-item">
                                   <div class="cart-product-section">
                                        <div class="cart-item-holder">
                                             <a href="#" class="cart-item-link">
                                                  <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-coarse-grind-sea-salt-349-oz-one-19555-19555-default-0-3692/4a146135b002f372a6a35292bee5bc13.jpg" class="cart-item-image" alt="Frontier Coarse Grind Sea Salt 3.49 oz., One, Size 3.49 Ounce">
                                             </a>
                                             <div class="cart-item-details">
                                                  <a href="#" class="cart-item-product-name">Frontier Coarse Grind Sea Salt 3.49 oz.</a>
                                                  
                                                  <span class="cart-item-number">Item #19555</span>
                                                  <a href="#" class="cart-item-wishlist-link">Move to Wishlist</a>
                                             </div>
                                        </div>
                                   </div>
                                   <div class="cart-notification-section">
                                        <span class="cart-message cart-savings-bulk">Your Bulk buy saved you 5%</span>
                                   </div>
                                   <div class="cart-qty-section">
                                        <span class="cart-qty-label">Qty.</span>
                                        <input type="number" min="1" name="item7496674" value="9" class="cart-qty-input form-control">
                                   </div>
                                   <div class="cart-item-break-section"></div>
                                   <div class="cart-price-section">
                                        <span class="cart-price-total-text unit-price">Unit Price:</span>
                                        <span class="cart-grid-sale">
                                             <span class="price-sale">$4.77</span> <span class="price-sale-now">Now $4.53</span>
                                        </span>
                                   </div>
                                   <div class="cart-price-total-section">
                                        <span class="cart-price-total-text">Total:</span> 
                                        <span class="cart-price-number-total">$40.77</span>
                                   </div>
                                   <div class="cart-remove-section">
                                        <a href="#" class="btn btn-only-green-icon" title="Remove">
                                             <span class="action-link-text">Remove</span>
                                             <span class="glyphicon glyphicon-remove"></span>
                                        </a>
                                   </div>
                              </div>

                         </div>

                         <div class="cart-subtotal-holder">
                              <div class="cart-subtotal-text">
                                   3 Item(s) Subtotal: $23.98
                              </div>
                         </div>

                         <div class="submit-holder">
                              <button class="btn btn-light-green btn-larger btn-cart-checkout">Checkout</button>
                         </div>
                         
                    </section>
               </div>
          </main>

          <!-- Rename Cart Modal -->
          <div class="modal fade" id="modalRenameCart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="myModalLabel">Rename Shopping Cart</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <form class="form-cart-order-rename">
                                   <div class="form-group">
                                        <label for="cart_name_change">New Cart Name</label>
                                        <input type="text" id="cart_name_change" class="form-control" placeholder="New Cart Title" name="cart_name_change">
                                   </div>
                              </form>
                         </div>
                         <div class="modal-footer clearfix">
                              <div class="modal-footer-btn-left-holder">
                                   <button type="button" class="btn btn-dark-orange btn-rename-cart-submit">Submit</button>
                              </div>
                              <div class="modal-footer-btn-right-holder">
                                   <button type="button" class="btn btn-link btn-link-close-modal" data-dismiss="modal">Cancel</button>
                              </div>
                         </div>
                    </div>
               </div>
          </div>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
          $(document).ready(function() {
               console.log('shopping cart doc ready');
          });
          </script>
     </body>
</html>
