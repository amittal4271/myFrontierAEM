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
                              <li class="active">Buying Club Membership</li>
                         </ol>
                    </div>
               </section>

               <section id="membership-holder">
                    <div class="container">
                         <div class="lifetime-leaf"></div>
                         <h1 class="plan-heading">Buying Club Account</h1>
                         <div class="membership-form-section">
                              <form id="lifetime-membership-form">

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Join a Buying Club</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <span class="btn btn-white-green btn-special-section pull-right">Login to Account</span>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Login Information</span>
                                        </legend>
                                        <div class="each-form-information">
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-email">Email address</label>
                                                  <input id="id_membership-email" class="form-control" name="membership-email" type="email">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_membership-password">Password</label>
                                                  <input id="id_membership-password" class="form-control" name="membership-password" type="password">
                                                  <small class="small-form-element">Make sure to use a strong password, 8 character minimum!</small>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_membership-password_confirm">Confirm Password</label>
                                                  <input id="id_membership-password_confirm" class="form-control" name="membership-password_confirm" type="password">
                                             </div>
                                        </div>
                                   </fieldset>

                                   <fieldset class="each-form-section">
                                        <legend class="each-form-title">
                                             <span class="title-text">Contact Information</span>
                                        </legend>
                                        <div class="each-form-information">
                                             
                                             <div class="form-group width-half first">
                                                  <label for="id_shipping-name">Full Name</label>
                                                  <input id="id_shipping-name" class="form-control" maxlength="150" name="shipping-name" type="text">
                                             </div>
                                             <div class="form-group width-half">
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
                                             <div class="submit-holder">
                                                  <button class="btn btn-white-green btn-account-form">Register</button>
                                             </div>
                                        </div>
                                   </fieldset>

                              </form>
                         </div>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
