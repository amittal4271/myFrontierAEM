<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/checkout-header.php');?>

          <main class="main-content-container account-section-max-1200">
               <div class="container">

                    <nav class="checkout-nav clearfix">
                         <ol class="checkout-nav-list">
                              <li class="checkout-nav-item completed">
                                   <a href="#">
                                        <div class="checkout-nav-step">
                                             <span class="checkout-nav-step-icon">1</span>
                                        </div>
                                        <span class="checkout-nav-info checkout-nav-info-1">Approval</span>
                                   </a>
                              </li>
                              <li class="checkout-nav-item completed">
                                   <a href="#">
                                        <div class="checkout-nav-step">
                                             <span class="checkout-nav-step-icon">2</span>
                                        </div>
                                        <span class="checkout-nav-info checkout-nav-info-2">Shipping</span>
                                   </a>
                              </li>
                              <li class="checkout-nav-item active">
                                   <div class="checkout-nav-step">
                                        <span class="checkout-nav-step-icon">3</span>
                                   </div>
                                   <span class="checkout-nav-info checkout-nav-info-3">Review &amp; Payments</span>
                              </li>
                         </ol>
                    </nav>

                    
                    <div id="checkout-message-holder" class="global-server-side-message-holder clearfix">
                         <div class="alert alert-danger" role="alert"> <strong>Message Alert:</strong> Some type of message that shows errors </div>
                    </div>
                    

                    <section class="account-main-holder clearfix checkout-holder shipping-page billing-page">
                         <div id="checkout-steps-holder">
                              <form>
                                   <div class="account-heading-holder completed-steps">
                                        <h2 class="account-heading"><span class="glyphicon glyphicon-ok"></span> 1. Shipping</h2>
                                   </div>
                                   
                                   <div class="account-heading-holder">
                                        <h2 class="account-heading">2. Billing / Payment / Review</h2>
                                   </div>

                                   <fieldset id="billing-address-holder" class="billing-section">
                                        <legend class="checkout-sub-header">Payment Method</legend>
                                        <div id="payment-method-options" class="clearfix">
                                             <div class="radio">
                                                  <label for="payment_method_0">
                                                       <input id="payment_method_0" name="payment_method" type="radio" value="">
                                                       <span class="label-text">Visa, Mastercard, American Express, Discover (image here)</span>
                                                  </label>
                                             </div>

                                             <div class="form-group width-half first">
                                                  <label for="id_billing-number">Credit Card Number</label>
                                                  <input id="id_billing-number" class="form-control" maxlength="20" name="billing-number" placeholder="**** **** **** ****" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-cvv">CVV Code</label>
                                                  <input id="id_billing-cvv" class="form-control" maxlength="4" name="billing-cvv" placeholder="***" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-exp_month">Expiration Month</label>
                                                  <select id="id_billing-exp_month" class="form-control" name="billing-exp_month">
                                                       <option value="1">01</option>
                                                       <option value="2">02</option>
                                                       <option value="3">03</option>
                                                       <option value="4">04</option>
                                                       <option value="5">05</option>
                                                       <option value="6">06</option>
                                                       <option value="7">07</option>
                                                       <option value="8">08</option>
                                                       <option value="9">09</option>
                                                       <option value="10">10</option>
                                                       <option value="11">11</option>
                                                       <option value="12">12</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-exp_year">Expiration Year</label>
                                                  <select id="id_billing-exp_year" class="form-control" name="billing-exp_year">
                                                       <option value="2018">2018</option>
                                                       <option value="2019">2019</option>
                                                       <option value="2020">2020</option>
                                                       <option value="2021">2021</option>
                                                       <option value="2022">2022</option>
                                                       <option value="2023">2023</option>
                                                       <option value="2024">2024</option>
                                                       <option value="2025">2025</option>
                                                       <option value="2026">2026</option>
                                                       <option value="2027">2027</option>
                                                  </select>
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_purchase_order_number">Purchase Order Number:</label>
                                                  <input id="id_purchase_order_number" maxlength="20" class="form-control" name="frontierauthnetcreditcard-purchase_order_number" type="text">
                                                  <small class="small-form-element">Businesses use purchase order (PO) numbers to track their orders. If you do not need a PO, simply add in any phrase or number of your choice.</small>
                                             </div>
                                        </div>
                                        
                                   </fieldset>

                                   <fieldset id="billing-address-holder" class="billing-section below-section">
                                        <legend class="checkout-sub-header">Billing Address</legend>
                                        <div id="radio-billing-address-options" class="clearfix">
                                             <div class="radio">
                                                  <label for="billing_address_0">
                                                       <input id="billing_address_0" name="existing_address" class="existing-billing-address-input" type="radio" value="">
                                                       <span class="label-text">(Contact) Justin Prahst, Justin Company, 1485 W 6th Ave, Columbus OH 43212-2429, USA</span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="billing_address_1">
                                                       <input id="billing_address_1" name="existing_address" class="existing-billing-address-input" type="radio" value="">
                                                       <span class="label-text">Justin Prahst, Test Company, 1485 W 6th Ave, Columbus OH 43212-2429, USA</span>
                                                  </label>
                                             </div>
                                             <div class="radio">
                                                  <label for="billing_address_2">
                                                       <input id="billing_address_2" name="existing_address" class="existing-billing-address-input new-address-input" type="radio" value="">
                                                       <span class="label-text">New Address</span>
                                                  </label>
                                             </div>
                                        </div>
                                        <div id="billing-new-address-holder">
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-company">Company</label>
                                                  <input id="id_billing-company" class="form-control" maxlength="30" name="billing-company" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-name">Full Name</label>
                                                  <input id="id_billing-name" class="form-control" maxlength="150" name="billing-name" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-address">Address Line 1</label>
                                                  <input id="id_billing-address" maxlength="150" class="form-control" name="billing-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-address2">Address Line 2</label>
                                                  <input id="id_billing-address2" class="form-control" maxlength="75" name="billing-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-city">City</label>
                                                  <input id="id_billing-city" class="form-control" maxlength="70" name="billing-city" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-locality">State</label>
                                                  <select id="id_billing-locality" class="form-control" name="billing-locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-postal_code">Zip</label>
                                                  <input id="id_billing-postal_code" class="form-control" name="billing-postal_code" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-country">Country</label>
                                                  <select id="id_billing-country" class="form-control" name="billing-country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_billing-phone">Phone</label>
                                                  <input id="id_billing-phone" class="form-control" name="billing-phone" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_billing-building">Building</label>
                                                  <select id="id_billing-building" class="form-control" name="billing-building">
                                                       <option value="1">Commercial</option>
                                                       <option value="5">Residential</option>
                                                  </select>
                                             </div>

                                        </div>
                                   </fieldset>

                                   <fieldset id="billing-address-holder" class="billing-section below-section">
                                        <legend class="checkout-sub-header">Sales Rep Assistance</legend>

                                        <div class="checkbox">
                                             <label for="id_sales-rep_assistance">
                                                  <input id="id_sales-rep_assistance" name="sales-rep_assistance" type="checkbox">
                                                  <span class="label-text">Click here if you'd like assistance from an Inside Sales Representative for merchandising aids such as displays, jars, labels, testers, etc. Your order will be delayed up to 24 hours.</span>
                                             </label>
                                        </div>

                                        <div id="sales-rep-textarea-holder">
                                             <div class="form-group">
                                                  <label for="id_sales-rep_message">Message</label>
                                                  <textarea id="id_sales-rep_message" class="form-control" name="sales-rep_message">
                                                  </textarea>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <div class="submit-holder checkout-flow">
                                        <button class="btn btn-light-green btn-larger btn-cart-checkout">Place Order</button>
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
                              <div class="submit-holder checkout-flow">
                                   <button class="btn btn-light-green btn-larger btn-cart-checkout">Place Order</button>
                              </div>
                         </aside>
                    </section>

               </div>
          </main>



          <?php include(dirname(__FILE__).'/snippets/checkout-footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>

          <script>
          $(document).ready(function() {
               console.log('checkout doc ready');

               $(document).on( "change", "#id_sales-rep_assistance", function(e) {
                    console.log('sales rep radio change');
                    var $this = $(this);

                    var $salesRepMessageHolder = $('#sales-rep-textarea-holder');
                    
                    if ($salesRepMessageHolder.hasClass('show-holder')) {
                         $salesRepMessageHolder.removeClass('show-holder').slideUp("fast");
                         $this.removeClass('sales-rep-selected');
                    } else {
                         $salesRepMessageHolder.addClass('show-holder').slideDown("fast");
                         $this.addClass('sales-rep-selected');
                    }
               });

          });
          </script>
     </body>
</html>
