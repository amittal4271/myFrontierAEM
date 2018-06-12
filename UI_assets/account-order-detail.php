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
                                                       <span class="tablet-below-col-heading">View Details</span>
                                                       <a href="#" class="btn btn-only-green-icon" title="View Details">
                                                            <span class="address-link-text mobile-button-text-description">View Details</span>
                                                            <span class="glyphicon glyphicon-minus"></span>
                                                       </a>
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
