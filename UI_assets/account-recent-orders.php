<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">

               <section class="account-page-holder">
                    <div class="container">
                         <?php include(dirname(__FILE__).'/snippets/account-nav.php');?>

                         <section class="account-main-holder">
                              
                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading">Recent Orders</h2>
                              </div>

                              <div class="account-information-holder">

                                   <div class="grid-table-header order-information-header">
                                        <div class="each-order-header recent-orders-header-web-order grid-header">Web Order</div>
                                        <div class="each-order-header recent-orders-header-date grid-header">Date</div>
                                        <div class="each-order-header recent-orders-header-order grid-header">Order</div>
                                        <div class="each-order-header recent-orders-header-ship grid-header">Ship</div>
                                        <div class="each-order-header recent-orders-header-ponumber grid-header">PO Number</div>

                                        <div class="each-order-header recent-orders-header-status grid-header">Status</div>
                                        <div class="each-order-header recent-orders-header-invoice grid-header">Invoice</div>
                                        <div class="each-order-header recent-orders-header-action grid-header">Actions</div>
                                   </div>
                                   <div class="order-grid grid-table-data invites-data">
                                        <div class="row-each-order">
                                             <div class="information-holder order-information-holder">
                                                  <div class="each-order-section recent-orders-web-order-section">
                                                       <span class="tablet-below-col-heading">Web Order</span>
                                                       <a class="table-link" href="#">648020</a>
                                                  </div>
                                                  <div class="each-order-section recent-orders-date-section">
                                                       <span class="tablet-below-col-heading">Date</span>
                                                       05/16/2018
                                                  </div>
                                                  <div class="each-order-section recent-orders-order-section">
                                                       <span class="tablet-below-col-heading">Order</span>
                                                       835396
                                                  </div>
                                                  <div class="each-order-section recent-orders-ship-section">
                                                       <span class="tablet-below-col-heading">Ship</span>
                                                       FedEx
                                                  </div>
                                                  <div class="each-order-section recent-orders-ponumber-section">
                                                       <span class="tablet-below-col-heading">PO Number</span>
                                                       51518
                                                  </div>
                                                  <div class="each-order-section recent-orders-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       Shipped
                                                  </div>
                                                  <div class="each-order-section recent-orders-invoice-section">
                                                       <span class="tablet-below-col-heading">Invoice</span>
                                                       <a class="table-link" href="#">2653430</a>
                                                  </div>
                                                  <div class="each-order-section recent-orders-action-section">
                                                       <div class="action-button-section-account">
                                                            <span class="tablet-below-col-heading">Reorder</span>
                                                            <a href="#" class="btn btn-light-green" title="Reorder">
                                                                 <span class="address-link-text">Reorder</span>
                                                            </a>
                                                       </div>
                                                       
                                                       <div class="action-button-section-account last">
                                                            <span class="tablet-below-col-heading">View</span>
                                                            <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                                 <span class="address-link-text visuallyhidden">Show</span>
                                                                 <span class="glyphicon rotate glyphicon-plus"></span>
                                                            </button>
                                                       </div>
                                                       
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-recent-header">
                                                       <div class="each-order-header recent-detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header recent-detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header recent-detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header recent-detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header recent-detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header recent-detail-header-past-qty grid-header">Past Qty</div>
                                                       <div class="each-order-header recent-detail-header-new-qty grid-header">New Qty</div>
                                                       <div class="each-order-header recent-detail-header-actions grid-header">Actions</div>
                                                  </div>
                                                  <div class="order-detail-grid">
                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-recent-section recent-detail-image-section">
                                                                      <a href="#">
                                                                           <img src="../frontierwholesales/images/pdp-related-two.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-past-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Past Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-new-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">New Qty</span>
                                                                      <input type="number" min="1" name="" value="1" class="recent-orders-qty form-control">
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-actions-section">
                                                                      <div class="action-button-section-account">
                                                                           <span class="tablet-below-col-heading">Add to Cart</span>
                                                                           <a href="#" class="btn btn-light-green" title="Add to Cart">
                                                                                <span class="address-link-text">Add to Cart</span>
                                                                           </a>
                                                                      </div>
                                                                      <div class="action-button-section-account last clear-desktop">
                                                                           <span class="tablet-below-col-heading">Add to Shelves</span>
                                                                           <a href="#" class="btn btn-green-dark" data-toggle="modal" data-target="#modalAddToShelf" title="Add to Shelves">
                                                                                <span class="address-link-text">Add to Shelves</span>
                                                                           </a>
                                                                      </div>
                                                                 </div>
                                                            </div>
                                                       </div>
                                                  </div>
                                             </div>
                                        </div>

                                        <div class="row-each-order">
                                             <div class="information-holder order-information-holder">
                                                  <div class="each-order-section recent-orders-web-order-section">
                                                       <span class="tablet-below-col-heading">Web Order</span>
                                                       <a class="table-link" href="#">648020</a>
                                                  </div>
                                                  <div class="each-order-section recent-orders-date-section">
                                                       <span class="tablet-below-col-heading">Date</span>
                                                       05/16/2018
                                                  </div>
                                                  <div class="each-order-section recent-orders-order-section">
                                                       <span class="tablet-below-col-heading">Order</span>
                                                       835396
                                                  </div>
                                                  <div class="each-order-section recent-orders-ship-section">
                                                       <span class="tablet-below-col-heading">Ship</span>
                                                       FedEx
                                                  </div>
                                                  <div class="each-order-section recent-orders-ponumber-section">
                                                       <span class="tablet-below-col-heading">PO Number</span>
                                                       51518
                                                  </div>
                                                  <div class="each-order-section recent-orders-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       Shipped
                                                  </div>
                                                  <div class="each-order-section recent-orders-invoice-section">
                                                       <span class="tablet-below-col-heading">Invoice</span>
                                                       <a class="table-link" href="#">2653430</a>
                                                  </div>
                                                  <div class="each-order-section recent-orders-action-section">
                                                       <div class="action-button-section-account">
                                                            <span class="tablet-below-col-heading">Reorder</span>
                                                            <a href="#" class="btn btn-light-green" title="Reorder">
                                                                 <span class="address-link-text">Reorder</span>
                                                            </a>
                                                       </div>
                                                       
                                                       <div class="action-button-section-account last">
                                                            <span class="tablet-below-col-heading">View</span>
                                                            <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                                 <span class="address-link-text visuallyhidden">Show</span>
                                                                 <span class="glyphicon rotate glyphicon-plus"></span>
                                                            </button>
                                                       </div>
                                                       
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-recent-header">
                                                       <div class="each-order-header recent-detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header recent-detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header recent-detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header recent-detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header recent-detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header recent-detail-header-past-qty grid-header">Past Qty</div>
                                                       <div class="each-order-header recent-detail-header-new-qty grid-header">New Qty</div>
                                                       <div class="each-order-header recent-detail-header-actions grid-header">Actions</div>
                                                  </div>
                                                  <div class="order-detail-grid">
                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-recent-section recent-detail-image-section">
                                                                      <a href="#">
                                                                           <img src="../frontierwholesales/images/pdp-related-two.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-past-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Past Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-new-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">New Qty</span>
                                                                      <input type="number" min="1" name="" value="1" class="recent-orders-qty form-control">
                                                                 </div>
                                                                 <div class="order-detail-recent-section recent-detail-actions-section">
                                                                      <div class="action-button-section-account">
                                                                           <span class="tablet-below-col-heading">Add to Cart</span>
                                                                           <a href="#" class="btn btn-light-green" title="Add to Cart">
                                                                                <span class="address-link-text">Add to Cart</span>
                                                                           </a>
                                                                      </div>
                                                                      <div class="action-button-section-account last clear-desktop">
                                                                           <span class="tablet-below-col-heading">Add to Shelves</span>
                                                                           <a href="#" class="btn btn-green-dark" data-toggle="modal" data-target="#modalAddToShelf" title="Add to Shelves">
                                                                                <span class="address-link-text">Add to Shelves</span>
                                                                           </a>
                                                                      </div>
                                                                 </div>
                                                            </div>
                                                       </div>
                                                  </div>
                                             </div>
                                        </div>
                                        
                                   </div>
                              </div>
                              
                         </section>
                    </div>
               </section>
          </main>

          <!-- Add to Shelf Modal Modal -->
          <div class="modal fade" id="modalAddToShelf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="myModalLabel">Add To Shelf</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <form class="form-cart-order-rename">
                                   <div class="form-group">
                                        <label for="cart_name_change">Select Shelf</label>
                                        <select id="wishlist-add-to-shelf" class="form-control">
                                             <option value="1">Shelf 1</option>
                                             <option value="2">Shelf 2</option>
                                        </select>
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
     </body>
</html>
