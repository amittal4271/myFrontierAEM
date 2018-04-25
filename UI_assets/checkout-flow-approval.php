<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/checkout-header.php');?>

          <main class="main-content-container account-section-max-1200">
               <div class="container">

                    <nav class="checkout-nav">
                         <ol class="checkout-nav-list">
                              <li class="checkout-nav-item active">
                                   <div class="checkout-nav-step">
                                        <span class="checkout-nav-step-icon">1</span>
                                   </div>
                                   <span class="checkout-nav-info checkout-nav-info-1">Approval</span>
                              </li>
                              <li class="checkout-nav-item">
                                   <div class="checkout-nav-step">
                                        <span class="checkout-nav-step-icon">2</span>
                                   </div>
                                   <span class="checkout-nav-info checkout-nav-info-2">Shipping</span>
                              </li>
                              <li class="checkout-nav-item">
                                   <div class="checkout-nav-step">
                                        <span class="checkout-nav-step-icon">3</span>
                                   </div>
                                   <span class="checkout-nav-info checkout-nav-info-3">Review &amp; Payments</span>
                              </li>
                         </ol>
                    </nav>

                    <div id="checkout-message-holder" class="global-server-side-message-holder">
                         <div class="alert alert-danger" role="alert"> <strong>Message Alert:</strong> Some type of message that shows errors </div>
                    </div>

                    <section class="account-main-holder checkout-holder">
                         <div class="each-order-by-user-holder">
                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading">Justin Prahst's Orders</h2>
                              </div>

                              <div class="orders-holder">
                                   <div class="grid-table-header order-information-header">
                                        <div class="each-order-header order-header-select grid-header">Select</div>
                                        <div class="each-order-header order-header-name grid-header">Order Name</div>
                                        <div class="each-order-header order-header-status grid-header">Status</div>
                                        <div class="each-order-header order-header-lines grid-header">Lines</div>
                                        <div class="each-order-header order-header-subtotal grid-header">Subtotal</div>
                                        <div class="each-order-header order-header-activity grid-header">Activity</div>
                                        <div class="each-order-header order-header-detail grid-header">Detail</div>
                                   </div>

                                   <div class="order-grid">

                                        <div class="row-each-order">
                                             <div class="order-information-holder">
                                                  <div class="each-order-section order-select-section">
                                                       <span class="tablet-below-col-heading">Select</span>
                                                       <div class="checkbox">
                                                            <label for="order_id_621322">
                                                                 <input type="checkbox" name="order_id" value="621322" id="order_id_621322">
                                                                 <span class="label-text hidden">Select Order #621322</span>
                                                            </label>
                                                       </div>
                                                  </div>
                                                  <div class="each-order-section order-name-section">
                                                       <span class="tablet-below-col-heading">Order Name</span>
                                                       <a class="link-switch-to-cart-orders" href="#">A Very New Order</a>
                                                  </div>
                                                  <div class="each-order-section order-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       <span class="order-text order-approved-text">Approved</span>
                                                  </div>
                                                  <div class="order-information-break-section"></div>
                                                  <div class="each-order-section order-lines-section">
                                                       <span class="tablet-below-col-heading">Lines</span>
                                                       <span class="order-text lines-text">2</span>
                                                  </div>
                                                  <div class="each-order-section order-subtotal-section">
                                                       <span class="tablet-below-col-heading">Subtotal</span>
                                                       <span class="order-text subtotal-text">$45.94</span>
                                                  </div>
                                                  <div class="each-order-section order-activity-section">
                                                       <span class="tablet-below-col-heading">Activity</span>
                                                       <span class="order-text activity-text">03/25/2018</span>
                                                  </div>
                                                  <div class="each-order-section order-detail-section">
                                                       <span class="tablet-below-col-heading">Details</span>
                                                       <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                            <span class="address-link-text visuallyhidden">Show</span>
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                       </button>
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-header">
                                                       <div class="each-order-header detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header detail-header-qty grid-header">Qty</div>
                                                  </div>
                                                  <div class="order-detail-grid">

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-adobo-seasoning-441-oz-one-18034-18034-default-0-485e/f047845f6adc28a926b6c5c6c8d6d49b.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-non-alcoholic-vanilla-flavoring-2-f-one-19492-19492-default-0-f79a/b81a24eabf5d61a2c98769cbf9795c42.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">32525</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Non-Alcoholic Vanilla Flavoring 2 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$6.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">3</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                  </div>
                                             </div>
                                        </div>


                                        <div class="row-each-order">
                                             <div class="order-information-holder">
                                                  <div class="each-order-section order-select-section">
                                                       <span class="tablet-below-col-heading">Select</span>
                                                       <div class="checkbox">
                                                            <label for="order_id_621322">
                                                                 <input type="checkbox" name="order_id" value="621322" id="order_id_621322">
                                                                 <span class="label-text hidden">Select Order #621322</span>
                                                            </label>
                                                       </div>
                                                  </div>
                                                  <div class="each-order-section order-name-section">
                                                       <span class="tablet-below-col-heading">Order Name</span>
                                                       <a class="link-switch-to-cart-orders" href="#">Justin's Renamed Cart</a>
                                                  </div>
                                                  <div class="each-order-section order-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       <span class="order-text order-approved-text">Approved</span>
                                                  </div>
                                                  <div class="order-information-break-section"></div>
                                                  <div class="each-order-section order-lines-section">
                                                       <span class="tablet-below-col-heading">Lines</span>
                                                       <span class="order-text lines-text">3</span>
                                                  </div>
                                                  <div class="each-order-section order-subtotal-section">
                                                       <span class="tablet-below-col-heading">Subtotal</span>
                                                       <span class="order-text subtotal-text">$29.94</span>
                                                  </div>
                                                  <div class="each-order-section order-activity-section">
                                                       <span class="tablet-below-col-heading">Activity</span>
                                                       <span class="order-text activity-text">03/24/2018</span>
                                                  </div>
                                                  <div class="each-order-section order-detail-section">
                                                       <span class="tablet-below-col-heading">Details</span>
                                                       <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                            <span class="address-link-text visuallyhidden">Show</span>
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                       </button>
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-header">
                                                       <div class="each-order-header detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header detail-header-qty grid-header">Qty</div>
                                                  </div>
                                                  <div class="order-detail-grid">

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-adobo-seasoning-441-oz-one-18034-18034-default-0-485e/f047845f6adc28a926b6c5c6c8d6d49b.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-non-alcoholic-vanilla-flavoring-2-f-one-19492-19492-default-0-f79a/b81a24eabf5d61a2c98769cbf9795c42.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">32525</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Non-Alcoholic Vanilla Flavoring 2 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$6.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">3</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-co-op-almond-flavor-16-fl-oz-one-23003-23003-default-0-5adf/9149829ed5728ad31a4ea89fe98df471.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">47744</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Frontier Co-op Almond Flavor 16 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$8.20</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">2</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                  </div>
                                             </div>
                                        </div>


                                   </div>
                              </div>
                         </div>



                         <div class="each-order-by-user-holder">
                              <div class="account-heading-holder table-above table-below">
                                   <h2 class="account-heading">Austin Power's Orders</h2>
                              </div>

                              <div class="orders-holder">
                                   <div class="grid-table-header order-information-header">
                                        <div class="each-order-header order-header-select grid-header">Select</div>
                                        <div class="each-order-header order-header-name grid-header">Order Name</div>
                                        <div class="each-order-header order-header-status grid-header">Status</div>
                                        <div class="each-order-header order-header-lines grid-header">Lines</div>
                                        <div class="each-order-header order-header-subtotal grid-header">Subtotal</div>
                                        <div class="each-order-header order-header-activity grid-header">Activity</div>
                                        <div class="each-order-header order-header-detail grid-header">Detail</div>
                                   </div>

                                   <div class="order-grid">

                                        <div class="row-each-order">
                                             <div class="order-information-holder">
                                                  <div class="each-order-section order-select-section">
                                                       <span class="tablet-below-col-heading">Select</span>
                                                       <div class="checkbox">
                                                            <label for="order_id_621322">
                                                                 <input type="checkbox" name="order_id" value="621322" id="order_id_621322">
                                                                 <span class="label-text hidden">Select Order #621322</span>
                                                            </label>
                                                       </div>
                                                  </div>
                                                  <div class="each-order-section order-name-section">
                                                       <span class="tablet-below-col-heading">Order Name</span>
                                                       <a class="link-switch-to-cart-orders" href="#">I Also Like to Live Dangerously Cart</a>
                                                  </div>
                                                  <div class="each-order-section order-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       <span class="order-text order-approved-text">Approved</span>
                                                  </div>
                                                  <div class="order-information-break-section"></div>
                                                  <div class="each-order-section order-lines-section">
                                                       <span class="tablet-below-col-heading">Lines</span>
                                                       <span class="order-text lines-text">2</span>
                                                  </div>
                                                  <div class="each-order-section order-subtotal-section">
                                                       <span class="tablet-below-col-heading">Subtotal</span>
                                                       <span class="order-text subtotal-text">$45.94</span>
                                                  </div>
                                                  <div class="each-order-section order-activity-section">
                                                       <span class="tablet-below-col-heading">Activity</span>
                                                       <span class="order-text activity-text">03/25/2018</span>
                                                  </div>
                                                  <div class="each-order-section order-detail-section">
                                                       <span class="tablet-below-col-heading">Details</span>
                                                       <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                            <span class="address-link-text visuallyhidden">Show</span>
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                       </button>
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-header">
                                                       <div class="each-order-header detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header detail-header-qty grid-header">Qty</div>
                                                  </div>
                                                  <div class="order-detail-grid">

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-adobo-seasoning-441-oz-one-18034-18034-default-0-485e/f047845f6adc28a926b6c5c6c8d6d49b.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-non-alcoholic-vanilla-flavoring-2-f-one-19492-19492-default-0-f79a/b81a24eabf5d61a2c98769cbf9795c42.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">32525</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Non-Alcoholic Vanilla Flavoring 2 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$6.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">3</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                  </div>
                                             </div>
                                        </div>


                                        <div class="row-each-order">
                                             <div class="order-information-holder">
                                                  <div class="each-order-section order-select-section">
                                                       <span class="tablet-below-col-heading">Select</span>
                                                       <div class="checkbox">
                                                            <label for="order_id_621322">
                                                                 <input type="checkbox" name="order_id" value="621322" id="order_id_621322">
                                                                 <span class="label-text hidden">Select Order #621322</span>
                                                            </label>
                                                       </div>
                                                  </div>
                                                  <div class="each-order-section order-name-section">
                                                       <span class="tablet-below-col-heading">Order Name</span>
                                                       <a class="link-switch-to-cart-orders" href="#">Dr. Evil's Cart</a>
                                                  </div>
                                                  <div class="each-order-section order-status-section">
                                                       <span class="tablet-below-col-heading">Status</span>
                                                       <span class="order-text order-approved-text">Approved</span>
                                                  </div>
                                                  <div class="order-information-break-section"></div>
                                                  <div class="each-order-section order-lines-section">
                                                       <span class="tablet-below-col-heading">Lines</span>
                                                       <span class="order-text lines-text">3</span>
                                                  </div>
                                                  <div class="each-order-section order-subtotal-section">
                                                       <span class="tablet-below-col-heading">Subtotal</span>
                                                       <span class="order-text subtotal-text">$29.94</span>
                                                  </div>
                                                  <div class="each-order-section order-activity-section">
                                                       <span class="tablet-below-col-heading">Activity</span>
                                                       <span class="order-text activity-text">03/24/2018</span>
                                                  </div>
                                                  <div class="each-order-section order-detail-section">
                                                       <span class="tablet-below-col-heading">Details</span>
                                                       <button class="btn btn-only-green-icon btn-toggle-order-details">
                                                            <span class="address-link-text visuallyhidden">Show</span>
                                                            <span class="glyphicon glyphicon-plus"></span>
                                                       </button>
                                                  </div>
                                             </div>
                                             <div class="order-details-holder">
                                                  <div class="grid-table-header order-detail-header">
                                                       <div class="each-order-header detail-header-image grid-header">&nbsp;</div>
                                                       <div class="each-order-header detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header detail-header-sku grid-header">SKU Status</div>
                                                       <div class="each-order-header detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header detail-header-qty grid-header">Qty</div>
                                                  </div>
                                                  <div class="order-detail-grid">

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-adobo-seasoning-441-oz-one-18034-18034-default-0-485e/f047845f6adc28a926b6c5c6c8d6d49b.jpg" class="image-pending-orders" alt="Simply Organic Adobo Seasoning 4.41 oz., One, Size 4.41 Ounce">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/simply/simply-organic-non-alcoholic-vanilla-flavoring-2-f-one-19492-19492-default-0-f79a/b81a24eabf5d61a2c98769cbf9795c42.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">32525</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Non-Alcoholic Vanilla Flavoring 2 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$6.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">3</span>
                                                                 </div>
                                                            </div>
                                                       </div>

                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section detail-image-section">
                                                                      <a href="#">
                                                                           <img src="https://dh6qdiyp93d9u.cloudfront.net/media/CACHE/images/products/fronti/frontier-co-op-almond-flavor-16-fl-oz-one-23003-23003-default-0-5adf/9149829ed5728ad31a4ea89fe98df471.jpg" class="image-pending-orders" alt="">
                                                                      </a>
                                                                 </div>
                                                                 <div class="order-detail-section detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">47744</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Frontier Co-op Almond Flavor 16 fl. oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-sku-section">
                                                                      <span class="tablet-below-col-detail-heading">Status</span>
                                                                      <span class="order-detail-text sku-status-text">In Stock</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$8.20</span>
                                                                 </div>
                                                                 <div class="order-detail-section detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty</span>
                                                                      <span class="order-detail-text qty-text">2</span>
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

                    <section class="cart-holder cart-actions">
                         <div class="cart-header-holder no-border">
                              <h1 class="cart-heading">Approval Actions</h1>
                         </div>
                         <div id="cart-actions-grid">
                              <div class="each-action-holder cart-action-status">
                                   <div class="action-holder">
                                        <a href="#" class="btn btn-dark-orange btn-checkout-approve-orders" data-toggle="modal" data-target="#modalApproveOrders">Approve Orders</a>
                                   </div>
                              </div>
                              <div class="each-action-holder cart-action-status">
                                   <div class="action-holder">
                                        <a href="#" class="btn btn-dark-orange btn-checkout-mark-draft" data-toggle="modal" data-target="#modalMarkDraftOrders">Mark as Draft</a>
                                   </div>
                              </div>
                              <div class="each-action-holder cart-action-status last">
                                   <div class="action-holder">
                                        <a href="#" class="btn btn-red btn-checkout-delete-orders" data-toggle="modal" data-target="#modalDeleteOrders">Delete Orders</a>
                                   </div>
                              </div>
                         </div>

                         <div class="submit-holder">
                              <button class="btn btn-light-green btn-larger btn-cart-checkout">Continue Checkout</button>
                         </div>

                    </section>

               </div>
          </main>

          <!-- Delete Order Modal -->
          <div class="modal fade" id="modalDeleteOrders" tabindex="-1" role="dialog" aria-labelledby="modalDeleteOrderLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="modalDeleteOrderLabel">Delete Confirmation</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <p>Messaging explaining delete action. This order will be removed from the users cart along with this cart.</p>
                         </div>
                         <div class="modal-footer clearfix">
                              <div class="modal-footer-btn-left-holder">
                                   <button type="button" class="btn btn-red btn-delete-order">Yes, Delete Orders</button>
                              </div>
                              <div class="modal-footer-btn-right-holder">
                                   <button type="button" class="btn btn-link btn-link-close-modal" data-dismiss="modal">Cancel</button>
                              </div>
                         </div>
                    </div>
               </div>
          </div>

          <!-- Approve Order Modal -->
          <div class="modal fade" id="modalApproveOrders" tabindex="-1" role="dialog" aria-labelledby="modalApproveOrderLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="modalApproveOrderLabel">Approval Confirmation</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <p>You are approving these orders for purchase. This is another explanation for the approval modal.</p>
                         </div>
                         <div class="modal-footer clearfix">
                              <div class="modal-footer-btn-left-holder">
                                   <button type="button" class="btn btn-dark-orange btn-delete-order">Yes, Approve Orders</button>
                              </div>
                              <div class="modal-footer-btn-right-holder">
                                   <button type="button" class="btn btn-link btn-link-close-modal" data-dismiss="modal">Cancel</button>
                              </div>
                         </div>
                    </div>
               </div>
          </div>

          <!-- Mark Draft Order Modal -->
          <div class="modal fade" id="modalMarkDraftOrders" tabindex="-1" role="dialog" aria-labelledby="modalMarkDraftOrdersLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="modalMarkDraftOrdersLabel">Mark as Draft</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <p>You are marking these orders as a draft. These will not be included in checkout cost.</p>
                         </div>
                         <div class="modal-footer clearfix">
                              <div class="modal-footer-btn-left-holder">
                                   <button type="button" class="btn btn-dark-orange btn-delete-order">Mark as Draft</button>
                              </div>
                              <div class="modal-footer-btn-right-holder">
                                   <button type="button" class="btn btn-link btn-link-close-modal" data-dismiss="modal">Cancel</button>
                              </div>
                         </div>
                    </div>
               </div>
          </div>

          <?php include(dirname(__FILE__).'/snippets/checkout-footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
          $(document).ready(function() {
               console.log('checkout doc ready');
          });
          </script>
     </body>
</html>
