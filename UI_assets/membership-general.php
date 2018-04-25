<!DOCTYPE html>
<html lang="en">
     <head>
          <?php include(dirname(__FILE__).'/snippets/header-files-include.php');?>
     </head>

     <body>

          <?php include(dirname(__FILE__).'/snippets/header.php');?>

          <main class="main-content-container account-section-max-1200">
               <section id="breadcrumbs">
                    <div class="container">
                         <ol class="breadcrumb">
                              <li><a href="#">Home</a></li>
                              <li><a href="#">Membership</a></li>
                              <li class="active">Lifetime Membership</li>
                         </ol>
                    </div>
               </section>

               <section id="membership-holder">
                    <div class="container">
                         <div class="lifetime-leaf"></div>
                         <h1 class="plan-heading">General Account</h1>
                         <div class="membership-form-section">
                              <form id="lifetime-membership-form">

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Type of Business</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="checkbox">
                                                  <label for="id_account-buying_club">
                                                       <input id="id_account-buying_club" name="account-buying_club" type="checkbox">
                                                       <span class="label-text">Buying Club</span>
                                                  </label>
                                             </div>
                                             <hr class="line-separator after-buying-club">
                                             <div class="radio-checkbox-section-holder thirds">
                                                  <div class="radio">
                                                       <label for="id_account-business_type_0">
                                                            <input id="id_account-business_type_0" name="account-business_type" type="radio" value="food-service">
                                                            <span class="label-text">Food Service</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_1">
                                                            <input id="id_account-business_type_1" name="account-business_type" type="radio" value="grocery">
                                                            <span class="label-text">Grocery</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_2">
                                                            <input id="id_account-business_type_2" name="account-business_type" type="radio" value="health">
                                                            <span class="label-text">Health Practitioner</span>
                                                       </label>
                                                  </div>


                                                  <div class="radio">
                                                       <label for="id_account-business_type_3">
                                                            <input id="id_account-business_type_3" name="account-business_type" type="radio" value="massage">
                                                            <span class="label-text">Massage/Spa</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_4">
                                                            <input id="id_account-business_type_4" name="account-business_type" type="radio" value="natural-foods">
                                                            <span class="label-text">Natural Foods Retailer</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_5">
                                                            <input id="id_account-business_type_5" name="account-business_type" type="radio" value="other">
                                                            <span class="label-text">Other</span>
                                                       </label>
                                                  </div>

                                                  <div class="radio">
                                                       <label for="id_account-business_type_6">
                                                            <input id="id_account-business_type_6" name="account-business_type" type="radio" value="retail">
                                                            <span class="label-text">Retail Business</span>
                                                       </label>
                                                  </div>
                                                  <div class="radio">
                                                       <label for="id_account-business_type_7">
                                                            <input id="id_account-business_type_7" name="account-business_type" type="radio" value="web">
                                                            <span class="label-text">Web Retailer</span>
                                                       </label>
                                                  </div>
                                             </div>
                                             <hr class="line-separator after-radio-section">
                                             <div class="checkbox width-one-third">
                                                  <label for="id_account-online_only">
                                                       <input id="id_account-online_only" name="account-online_only" type="checkbox">
                                                       <span class="label-text">Online Only Store</span>
                                                  </label>
                                             </div>
                                             <div class="form-group width-two-thirds">
                                                  <label for="id_account-url">Web Address</label>
                                                  <input id="id_account-url" class="form-control" name="account-url" placeholder="www.youronlinestore.com" type="text">
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Billing Address</span>

                                             <p class="form-title-information">Note: Canadian orders must be phoned (800) 669-3275 or faxed (800) 717-4372 to our Customer Service Department, because we are unable to calculate shipping charges online.</p>

                                             <p class="form-title-information">Frontier Co-op does not currently sell to manufacturers of industrial suppliers.</p>

                                             <p class="form-title-information">Unless we have a valid signed sales tax exemption form covering the address to which an order is shipped, we charge applicable sales tax in California, Colorado, Illinois, Iowa, Texas, Virginia, and Washington. <a href="//dh6qdiyp93d9u.cloudfront.net/media/files/membership-sale-tax-uniform-exemption-19-d1f5.pdf" target="_blank" rel="noopener noreferrer">Sales Tax Uniform Exemption Form</a> 
                                             </p>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="checkbox">
                                                  <label for="id_shipping-shipping_same">
                                                       <input id="id_shipping-shipping_same" name="shipping-shipping_same" type="checkbox">
                                                       <span class="label-text">Same as Tax Payer Mailing Address</span>
                                                  </label>
                                             </div>
                                             <hr class="line-separator after-buying-club">
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-name">Full Name</label>
                                                  <input id="id_shipping-name" class="form-control" maxlength="150" name="shipping-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-company">Company</label>
                                                  <input id="id_shipping-company" class="form-control" maxlength="30" name="shipping-company" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_shipping-address">Address Line 1</label>
                                                  <input id="id_shipping-address" maxlength="150" class="form-control" name="shipping-address" placeholder="123 Example Street" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-address2">Address Line 2</label>
                                                  <input id="id_shipping-address2" class="form-control" maxlength="75" name="shipping-address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-city">City</label>
                                                  <input id="id_shipping-city" class="form-control" maxlength="70" name="shipping-city" type="text">
                                             </div>
                                             <div class="form-group width-half first">
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
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-postal_code">Zip</label>
                                                  <input id="id_shipping-postal_code" class="form-control" name="shipping-postal_code" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-country">Country</label>
                                                  <select id="id_shipping-country" class="form-control" name="shipping-country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_shipping-phone">Phone</label>
                                                  <input id="id_shipping-phone" class="form-control" name="shipping-phone" type="text">
                                             </div>
                                             <div class="form-group">
                                                  <div class="lifetime-membership-disclaimer">
                                                       Under penalties of perjury, I certify that:
                                                       <ol class="lifetime-membership-disclaimer-list">
                                                            <li>The number shown is my correct taxpayer identification number (or I am waiting for a number to be issued to me).</li>
                                                            <li>I am not subject to backup withholding because: (a) I am exempt from backup withholding, or (b) I have not been notified by the Internal Revenue Service that I am subject backup withholding as a result of a failure to report all interest or dividends, or (c) the IRS has notified me that I am no longer subject to breakup withholding.</li>
                                                       </ol>
                                                       
                                                       <p class="lifetime-membership-disclaimer-acknowledge">I acknowledge that by typing my name below, I am indicating my intent to sign the document and that this shall constitute my signature.</p>
                                                  </div>
                                             </div>
                                             <div class="form-group">
                                                  <label for="id_account-signature"><strong>Signature</strong></label>
                                                  <input id="id_account-signature" class="form-control" name="account-signature" type="text">
                                             </div>
                                             <div class="checkbox">
                                                  <label for="id_account-credit_terms">
                                                       <input id="id_account-credit_terms" name="account-credit_terms" type="checkbox">
                                                       <span class="label-text">Do you wish to be considered for credit terms? Approx. 30 business days for approval.</span>
                                                  </label>
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Create Online Account Access</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-name">Name</label>
                                                  <input id="id_membership-name" class="form-control" name="membership-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_membership-email">Email</label>
                                                  <input id="id_membership-email" class="form-control" name="membership-email" type="email">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-password">Password</label>
                                                  <input id="id_membership-password" class="form-control" name="membership-password" type="password">
                                                  <small class="small-form-element">Make sure to use a strong password, 8 character minimum!</small>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_membership-password_confirm">Confirm Password</label>
                                                  <input id="id_membership-password_confirm" class="form-control" name="membership-password_confirm" type="password">
                                             </div>
                                        </div>
                                   </fieldset>

                                   <div class="submit-holder">
                                        <button class="btn btn-light-green btn-account-form">Submit for Approval</button>
                                   </div>

                              </form>
                         </div>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
