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
                              <li class="checkout-nav-item completed">
                                   <a href="#">
                                        <div class="checkout-nav-step">
                                             <span class="checkout-nav-step-icon">1</span>
                                        </div>
                                        <span class="checkout-nav-info checkout-nav-info-1">Approval</span>
                                   </a>
                              </li>
                              <li class="checkout-nav-item active">
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
                    

                    <section class="account-main-holder checkout-holder shipping-page">
                         <div id="checkout-steps-holder">
                              <form>
                                   <div class="account-heading-holder">
                                        <h2 class="account-heading">1. Shipping</h2>
                                   </div>
                                   <fieldset id="shipping-address-holder" class="shipping-section">
                                        <legend class="checkout-sub-header">Shipping Address</legend>
                                        <div id="radio-shipping-address-options" class="clearfix">
                                             <div class="radio">
                                                  <label for="shipping_address_0">
                                                       <input id="shipping_address_0" name="existing_address" class="existing-shipping-address-input" type="radio" value="">
                                                       <span class="label-text">(Contact) Justin Prahst, Justin Company, 1485 W 6th Ave, Columbus OH 43212-2429, USA</span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="shipping_address_1">
                                                       <input id="shipping_address_1" name="existing_address" class="existing-shipping-address-input" type="radio" value="">
                                                       <span class="label-text">Justin Prahst, Test Company, 1485 W 6th Ave, Columbus OH 43212-2429, USA</span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="shipping_address_2">
                                                       <input id="shipping_address_2" name="existing_address" class="existing-shipping-address-input new-address-input" type="radio" value="">
                                                       <span class="label-text">New Address</span>
                                                  </label>
                                             </div>
                                        </div>
                                        <div id="shipping-new-address-holder">
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-company">Company</label>
                                                  <input id="id_shipping-company" class="form-control" maxlength="30" name="shipping-company" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-name">Full Name</label>
                                                  <input id="id_shipping-name" class="form-control" maxlength="150" name="shipping-name" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-address">Address Line 1</label>
                                                  <input id="id_shipping-address" maxlength="150" class="form-control" name="shipping-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-address2">Address Line 2</label>
                                                  <input id="id_shipping-address2" class="form-control" maxlength="75" name="shipping-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-city">City</label>
                                                  <input id="id_shipping-city" class="form-control" maxlength="70" name="shipping-city" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-locality">State</label>
                                                  <select id="id_shipping-locality" class="form-control" name="shipping-locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-postal_code">Zip</label>
                                                  <input id="id_shipping-postal_code" class="form-control" name="shipping-postal_code" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-country">Country</label>
                                                  <select id="id_shipping-country" class="form-control" name="shipping-country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-phone">Phone</label>
                                                  <input id="id_shipping-phone" class="form-control" name="shipping-phone" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-building">Building</label>
                                                  <select id="id_shipping-building" class="form-control" name="shipping-building">
                                                       <option value="1">Commercial</option>
                                                       <option value="5">Residential</option>
                                                  </select>
                                             </div>

                                             <button type="button" class="btn btn-white-green" data-toggle="modal" data-target="#modalAddressConfirmation">Test Address Verification</button>
                                        </div>
                                   </fieldset>

                                   <fieldset id="shipping-rate-holder" class="shipping-section below-section">
                                        <legend class="checkout-sub-header">Delivery Method</legend>
                                        <div id="radio-shipping-rate-options" class="clearfix">
                                             <!--Loader can be shown and hidden for ajax calls / injected to the DOM when waiting for an ajax response-->
                                             <div class="loader"></div>

                                             <!--These radio options will be updated via ajax-->
                                             <div class="radio">
                                                  <label for="shipping_rate_0">
                                                       <input id="shipping_rate_0" name="shipping_rate" type="radio" value="">
                                                       <span class="label-text">FedEx Ground Residential - <span class="bold">$21.22</span></span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="shipping_rate_1">
                                                       <input id="shipping_rate_1" name="shipping_rate" type="radio" value="">
                                                       <span class="label-text">UPS Ground - <span class="bold">$24.22</span></span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="shipping_rate_2">
                                                       <input id="shipping_rate_2" name="shipping_rate" type="radio" value="">
                                                       <span class="label-text">Frontier Pick-up at 3021 78th St. Norway, IA 52318 - <span class="bold">$24.22</span> -  (Please call prior to pick up)</span>
                                                  </label>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <div class="submit-holder checkout-flow">
                                        <button class="btn btn-light-green btn-larger btn-cart-checkout">Continue to Next Step</button>
                                   </div>

                                   <div class="account-heading-holder checkout-next-steps">
                                        <h2 class="account-heading">2. Billing / Payment / Review</h2>
                                   </div>

                              </form>
                         </div>
                         <aside id="cart-order-summary-holder">
                              <h3 class="checkout-sub-header">Your Cart</h3>
                              <div class="order-summary-holder">
                                   <table class="table-order-summary">
                                        <colgroup>
                                             <col class="description">
                                             <col class="cost">
                                        </colgroup>
                                        <thead>
                                             <tr>
                                                  <th>Description</th>
                                                  <th>Cost</th>
                                             </tr>
                                        </thead>
                                        <tbody>
                                             <tr class="subtotal">
                                                  <td>27 Item Subtotal</td>
                                                  <td class="amount">$199.64</td>
                                             </tr>
                                             <tr class="shipping">
                                                  <td class="method">Shipping</td>
                                                  <td class="amount">$24.11</td>
                                             </tr>

                                             <tr class="tax">
                                                  <td>Tax</td>
                                                  <td class="amount">$0.00</td>
                                             </tr>

                                             <tr class="discounts">
                                                  <td>Discounts</td>
                                                  <td class="amount">-$10.00</td>
                                             </tr>
                                        </tbody>
                                        <tfoot>
                                             <tr class="total">
                                                  <td>
                                                       <strong>Total</strong>
                                                  </td>
                                                  <td class="amount">
                                                       <strong class="total-amount">$223.75</strong>
                                                  </td>
                                             </tr>
                                        </tfoot>
                                   </table>
                              </div>
                         </aside>
                    </section>

               </div>
          </main>

          <!-- Address Confirmation Modal -->
          <div class="modal fade" id="modalAddressConfirmation" tabindex="-1" role="dialog" aria-labelledby="modalAddressConfirmationLabel">
               <div class="modal-dialog" role="document">
                    <div class="modal-content">
                         <div class="modal-header clearfix">
                              <h4 class="modal-title" id="modalAddressConfirmationLabel">Address Validation</h4>
                         </div>
                         <div class="modal-body clearfix">
                              <div id="original-addresses" class="modal-address-section">
                                   <p class="address-suggestion-header">Use Original Address</p>
                                   <div class="radio">
                                        <label for="origin_address">
                                             <input id="origin_address" name="candidate" type="radio" value="">
                                             <span class="label-text">10455 Lake Meadows Drive, Strongsville OH, 44136, United States</span>
                                        </label>
                                   </div>
                              </div>
                              <div id="suggested-addresses" class="modal-address-section">
                                   <p class="address-suggestion-header">Use Suggested Address</p>
                                   <div class="radio">
                                        <label for="candidate_0">
                                             <input id="candidate_0" name="candidate" type="radio" value="">
                                             <span class="label-text">10455 Lake Meadows Drive, Strongsville OH, 44136, United States</span>
                                        </label>
                                   </div>
                                   <div class="radio">
                                        <label for="candidate_1">
                                             <input id="candidate_1" name="candidate" type="radio" value="">
                                             <span class="label-text">10455 Lake Meadows Drive, Strongsville OH, 44136, United States</span>
                                        </label>
                                   </div>
                              </div>
                         </div>
                         <div class="modal-footer clearfix">
                              <div class="modal-footer-btn-left-holder">
                                   <button type="button" class="btn btn-dark-orange btn-continue-address-validation">Continue</button>
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
