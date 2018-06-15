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

                         <section id="my-account-message-area">
                              <div class="account-message-holder">
                                   <div class="account-message-title">Message From Frontier</div>
                                   <p class="account-message-p">Due to the Easter holiday, the Monthly Sales category will be updated on Monday, April 2nd. For questions or help ordering, reach out to our Customer Service or Inside Sales teams at customercare@frontiercoop.com or 1-800-669-3275.
                                   </p>
                                   <ul class="account-message-list">
                                        <li>List Item One</li>
                                        <li>List Item Two</li>
                                   </ul>
                              </div>
                         </section>

                         <section class="account-main-holder clearfix">

                              <div class="account-heading-holder table-below">
                                   <h2 class="account-heading">Pending Orders</h2>
                                   <a href="#" class="btn btn-white-green btn-account-heading btn-account-form">View All Orders</a>
                              </div>

                              <div class="grid-table-header order-information-header">
                                   <div class="each-order-header landing-pending-header-shopper grid-header">Shopper</div>
                                   <div class="each-order-header landing-pending-header-ordername grid-header">Order Name</div>
                                   <div class="each-order-header landing-pending-header-lines grid-header">Lines</div>
                                   <div class="each-order-header landing-pending-header-subtotal grid-header">Subtotal</div>
                                   <div class="each-order-header landing-pending-header-status grid-header">Status</div>
                                   <div class="each-order-header landing-pending-header-actions grid-header">Actions</div>
                              </div>

                              <div class="order-grid grid-table-data invites-data">
                                   <div class="row-each-order">
                                        <div class="information-holder order-information-holder">
                                             <div class="each-order-section landing-pending-shopper-section">
                                                  <span class="tablet-below-col-heading">Shopper</span>
                                                  Justin Prahst
                                             </div>
                                             <div class="each-order-section landing-pending-ordername-section">
                                                  <span class="tablet-below-col-heading">Order Name</span>
                                                  Justin's created on 02/22/1
                                             </div>
                                             <div class="each-order-section landing-pending-lines-section">
                                                  <span class="tablet-below-col-heading">Lines</span>
                                                  3
                                             </div>
                                             <div class="each-order-section landing-pending-subtotal-section">
                                                  <span class="tablet-below-col-heading">Subtotal</span>
                                                  $123.33
                                             </div>
                                             <div class="each-order-section landing-pending-status-section">
                                                  <span class="tablet-below-col-heading">Status</span>
                                                  Draft
                                             </div>
                                             <div class="each-order-section landing-pending-actions-section">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </div>
                                        </div>
                                   </div>
                                   <div class="row-each-order">
                                        <div class="information-holder order-information-holder">
                                             <div class="each-order-section landing-pending-shopper-section">
                                                  <span class="tablet-below-col-heading">Shopper</span>
                                                  Brittany Miller
                                             </div>
                                             <div class="each-order-section landing-pending-ordername-section">
                                                  <span class="tablet-below-col-heading">Order Name</span>
                                                  Brittany's Cart created on 02/22/1
                                             </div>
                                             <div class="each-order-section landing-pending-lines-section">
                                                  <span class="tablet-below-col-heading">Lines</span>
                                                  4
                                             </div>
                                             <div class="each-order-section landing-pending-subtotal-section">
                                                  <span class="tablet-below-col-heading">Subtotal</span>
                                                  $523.33
                                             </div>
                                             <div class="each-order-section landing-pending-status-section">
                                                  <span class="tablet-below-col-heading">Status</span>
                                                  Draft
                                             </div>
                                             <div class="each-order-section landing-pending-actions-section">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </div>
                                        </div>
                                   </div>
                                   <div class="row-each-order">
                                        <div class="information-holder order-information-holder">
                                             <div class="each-order-section landing-pending-shopper-section">
                                                  <span class="tablet-below-col-heading">Shopper</span>
                                                  Brittany Miller
                                             </div>
                                             <div class="each-order-section landing-pending-ordername-section">
                                                  <span class="tablet-below-col-heading">Order Name</span>
                                                  Brittany's Cart created on 02/22/1
                                             </div>
                                             <div class="each-order-section landing-pending-lines-section">
                                                  <span class="tablet-below-col-heading">Lines</span>
                                                  4
                                             </div>
                                             <div class="each-order-section landing-pending-subtotal-section">
                                                  <span class="tablet-below-col-heading">Subtotal</span>
                                                  $523.33
                                             </div>
                                             <div class="each-order-section landing-pending-status-section">
                                                  <span class="tablet-below-col-heading">Status</span>
                                                  Draft
                                             </div>
                                             <div class="each-order-section landing-pending-actions-section">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </div>
                                        </div>
                                   </div>
                              </div>

                              <!--

                              <table class="simple-responsive">
                                   <thead>
                                        <tr>
                                             <th scope="col" class="orders-placed-by-th">Shopper</th>
                                             <th scope="col" class="orders-order-name-th">Order Name</th>
                                             <th scope="col">Lines</th>
                                             <th scope="col">Subtotal</th>
                                             <th scope="col">Status</th>
                                             <th scope="col">Actions</th>
                                        </tr>
                                   </thead>
                                   <tbody>
                                        <tr>
                                             <td scope="row" class="orders-placed-by-td" data-label="Shopper">Brittany Miller</td>
                                             <td  class="orders-order-name-td" data-label="Order Name">Brittany's Cart created on 02/22/18</td>
                                             <td data-label="Lines">6</td>
                                             <td data-label="Subtotal">$513.78</td>
                                             <td data-label="Status">Draft</td>
                                             <td data-label="Actions">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </td>
                                        </tr>
                                        <tr>
                                             <td scope="row" class="orders-placed-by-td" data-label="Shopper">Brittany Miller</td>
                                             <td  class="orders-order-name-td" data-label="Order Name">Brittany's Cart created on 02/22/18</td>
                                             <td data-label="Lines">6</td>
                                             <td data-label="Subtotal">$513.78</td>
                                             <td data-label="Status">Draft</td>
                                             <td data-label="Actions">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </td>
                                        </tr>
                                        <tr>
                                             <td scope="row" class="orders-placed-by-td" data-label="Shopper">Brittany Miller</td>
                                             <td  class="orders-order-name-td" data-label="Order Name">Brittany's Cart created on 02/22/18</td>
                                             <td data-label="Lines">6</td>
                                             <td data-label="Subtotal">$513.78</td>
                                             <td data-label="Status">Draft</td>
                                             <td data-label="Actions">
                                                  <form id="form-order-switch">
                                                       <button class="btn btn-light-green btn-view-order">View Order</button>
                                                  </form>
                                             </td>
                                        </tr>
                                   </tbody>
                              </table>

                              -->
                              
                         </section>
                    </div>
               </section>

          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
