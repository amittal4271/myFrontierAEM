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

                         <section class="account-main-holder clearfix">
                              
                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading">Order #123456</h2>
                                   <a href="#" class="btn btn-white-green btn-account-heading btn-account-form">Back To Recent Orders</a>
                              </div>

                              <div class="account-information-holder">

                                   <div class="grid-table-header order-information-header">
                                        <div class="each-order-header order-detail-header-web-order grid-header">Web Order</div>
                                        <div class="each-order-header order-detail-header-date grid-header">Date</div>
                                        <div class="each-order-header order-detail-header-order grid-header">Order</div>
                                        <div class="each-order-header order-detail-header-ship grid-header">Ship</div>
                                        <div class="each-order-header order-detail-header-status grid-header">Status</div>
                                        <div class="each-order-header order-detail-header-actions grid-header">Detail</div>
                                   </div>
                                   <div class="order-grid grid-table-data invites-data">
                                        <div class="row-each-order">
                                             <div class="information-holder order-information-holder">
                                                  <div class="each-order-section order-detail-web-order-section">
                                                       <!--<span class="tablet-below-col-heading">First</span>-->
                                                       123456
                                                  </div>
                                                  <div class="each-order-section order-detail-date-section">
                                                       <!--<span class="tablet-below-col-heading">Last</span>-->
                                                       01/01/2018
                                                  </div>
                                                  <div class="each-order-section order-detail-order-section">
                                                       <!--<span class="tablet-below-col-heading">Email</span>-->
                                                       835396
                                                  </div>
                                                  <div class="each-order-section order-detail-ship-section">
                                                       <!--<span class="tablet-below-col-heading">Status</span>-->
                                                       Fedex
                                                  </div>
                                                  <div class="each-order-section order-detail-status-section">
                                                       <!--<span class="tablet-below-col-heading">Status</span>-->
                                                       Shipped
                                                  </div>
                                                  <div class="each-order-section order-detail-action-section">
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
                                                       <div class="each-order-header order-detail-header-item grid-header">Item #</div>
                                                       <div class="each-order-header order-detail-header-description grid-header">Description</div>
                                                       <div class="each-order-header order-detail-header-price grid-header">Price</div>
                                                       <div class="each-order-header order-detail-header-qty grid-header">Qty.</div>
                                                       <div class="each-order-header order-detail-header-subtotal grid-header">Subtotal</div>
                                                       <div class="each-order-header order-detail-header-tax grid-header">Tax</div>
                                                  </div>
                                                  <div class="order-detail-grid">
                                                       <div class="row-each-order-detail">
                                                            <div class="order-detail-holder">
                                                                 <div class="order-detail-section order-detail-item-section">
                                                                      <span class="tablet-below-col-detail-heading">Item #</span>
                                                                      <span class="order-detail-text item-number-text">18034</span>
                                                                 </div>
                                                                 <div class="order-detail-section order-detail-description-section">
                                                                      <span class="tablet-below-col-detail-heading">Description</span>
                                                                      <span class="order-detail-text description-text">Simply Organic Adobo Seasoning 4.41 oz.</span>
                                                                 </div>
                                                                 <div class="order-detail-section order-detail-price-section">
                                                                      <span class="tablet-below-col-detail-heading">Price</span>
                                                                      <span class="order-detail-text price-text">$4.48</span>
                                                                 </div>
                                                                 <div class="order-detail-section order-detail-qty-section">
                                                                      <span class="tablet-below-col-detail-heading">Qty.</span>
                                                                      <span class="order-detail-text qty-text">1</span>
                                                                 </div>
                                                                 <div class="order-detail-section order-detail-subtotal-section">
                                                                      <span class="tablet-below-col-detail-heading">Subtotal</span>
                                                                      <span class="order-detail-text subtotal-text">$7.50</span>
                                                                 </div>
                                                                 <div class="order-detail-section order-detail-tax-section">
                                                                      <span class="tablet-below-col-detail-heading">Tax</span>
                                                                      <span class="order-detail-text tax-text">$0.00</span>
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
