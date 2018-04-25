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
                              <div class="account-heading-holder">
                                   <h2 class="account-heading">Contact Information</h2>
                              </div>
                              <div class="account-information-holder">
                                   <form class="account-form" id="form-contact-information">
                                        <fieldset class="account-fieldset-third-holder">
                                             <div class="form-group width-half first">
                                                  <label for="id_company">Company</label>
                                                  <input id="id_company" maxlength="30" class="form-control" name="company" type="text" value="">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_name">Full Name</label>
                                                  <input id="id_name" maxlength="70" class="form-control" name="name" type="text" value="">
                                             </div>
                                             
                                             <div class="form-group width-half first">
                                                  <label for="id_address">Address Line 1</label>
                                                  <input id="id_address" maxlength="150" class="form-control" name="address" placeholder="123 Example Street" type="text" value="">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_address2">Address Line 2</label>
                                                  <input id="id_address2" maxlength="75" class="form-control" name="address2" placeholder="Suite 55C" type="text">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_city">City</label>
                                                  <input id="id_city" maxlength="70" class="form-control" name="city" type="text" value="">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_locality">State</label>
                                                  <select id="id_locality" class="form-control" name="locality">
                                                       <option value="1">Alabama</option>
                                                       <option value="5">Alaska</option>
                                                       <option value="275">American Samoa</option>
                                                       <option value="6">Arizona</option>
                                                       <option value="7">Arkansas</option>
                                                       <option value="281">Armed Forces Americas</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_postal_code">Zip</label>
                                                  <input id="id_postal_code" class="form-control" name="postal_code" type="text" value="">
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_country">Country</label>
                                                  <select id="id_country" class="form-control" name="country">
                                                       <option value="840" selected="selected">United States of America</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_commercial">Building</label>
                                                  <select id="id_commercial" class="form-control" name="commercial">
                                                       <option value="True">Commercial</option>
                                                       <option value="False">Residential</option>
                                                  </select>
                                             </div>
                                             <div class="form-group width-half">
                                                  <label for="id_phone">Phone</label>
                                                  <input id="id_phone" class="form-control" name="phone" type="text" value="">
                                             </div>
                                             <div class="form-group width-half first">
                                                  <label for="id_fax">Fax</label>
                                                  <input id="id_fax" class="form-control" name="fax" type="text">
                                             </div>
                                             <div class="form-group button-holder">
                                                  <input type="submit" class="btn btn-white-green btn-account-form" value="Save">
                                             </div>
                                        </fieldset>
                                   </form>
                              </div>
                              
                         </section>
                    </div>
               </section>
          </main>

          <?php include(dirname(__FILE__).'/snippets/footer.php');?>

          <?php include(dirname(__FILE__).'/snippets/footer-files-include.php');?>
     </body>
</html>
