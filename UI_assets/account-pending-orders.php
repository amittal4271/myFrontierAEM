<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
          <style>
          #sub-nav-holder-desktop {display: none;}
          </style>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">

               <section class="account-page-holder">
                    <div class="container">
                         <?php include(dirname(__FILE__).'/snippets/account-nav.php');?>

                         <section class="account-main-holder">

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
                                                                 <label for="order_id_0000">
                                                                      <input type="checkbox" name="order_id_number_0000">
                                                                      <span class="label-text">Select</span>
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
                                                                 <label for="order_id_1111">
                                                                      <input type="checkbox" name="order_id_number_1111" value="1111">
                                                                      <span class="label-text">Select</span>
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
                                                                 <label for="order_id_2222">
                                                                      <input type="checkbox" name="order_id" value="2222" id="order_id_2222">
                                                                      <span class="label-text">Select</span>
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
                                                                 <label for="order_id_3333">
                                                                      <input type="checkbox" name="order_id_number_3333" value="3333">
                                                                      <span class="label-text">Select</span>
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
                    </div>
               </section>

          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
